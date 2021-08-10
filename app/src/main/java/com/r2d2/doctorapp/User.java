package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** A user of the app. Subclasses may add additional fields with a subclass of {@code Profile}. */
public abstract class User {

    /** A read-only record of user information. */
    public static class Profile implements Serializable {
        private String firstName = "";
        private String lastName = "";
        private String username = "";
        private String password = "";
        private String gender = "Other";
        private int sin;
        protected ArrayList<Appointment> appointments = new ArrayList<>();
        // appointments[56]: 7 days a week, 8 timeslots a day. (9 ~ 17)

        public Profile() {
        }

        public Profile(String firstName, String lastName, String username, String password, String gender, int sin, ArrayList<Appointment> appointments) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.gender = gender;
            this.sin = sin;
            this.appointments = appointments;
        }

        public ArrayList<Appointment> getAppointments() {
            return appointments;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getGender() {
            return gender;
        }

        public int getSin() {
            return sin;
        }
    }

    /** The {@code Profile} subclass to read from the database. */
    protected abstract Class<? extends Profile> profileClass();

    /**
     * Instantiates a new (empty) {@code Profile} subclass.
     * The result should be an instance of the class returned by {@code profileClass()}.
     */
    protected abstract Profile newProfile();

    private final DatabaseReference ref;
    private Profile profile;

    /**
     * Construct a User that tracks {@code ref}.
     * @param ref database reference to user data
     */
    public User(DatabaseReference ref, String username) {
        this.ref = ref;
        this.profile = newProfile();
        this.profile.username = username;
        listenForValueEvents();
    }

    // this is for creating a User (Doctor/patient) from a profile
    /**
     * Construct a User with profile {@code profile}.
     * @param ref database reference to write user data to, and then track
     * @param profile user data to write
     */
    public User(DatabaseReference ref, Profile profile) {
        this.ref = ref;
        this.profile = profile;
        pushToDatabase();
        listenForValueEvents();
    }

    private void listenForValueEvents() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("User onDataChange", snapshot.toString());
                Profile newProfile = snapshot.getValue(profileClass());
                if (newProfile != null)
                    profile = newProfile;

                for (Runnable observer : observers) {
                    observer.run();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("User onCancelled", error.toException());
            }
        });
    }

    private final List<Runnable> observers = new ArrayList<>();

    /**
     * Observe this object to perform an action when new data arrives.
     * @param action function to run when new data arrives
     */
    public void addObserver(Runnable action) {
        observers.add(action);
    }

    /**
     * Observe this object to perform an action exactly once, when new data arrives.
     * @param action function to run exactly once, when new data arrives
     */
    public void addOneTimeObserver(Runnable action) {
        observers.add(new Runnable() {
            @Override
            public void run() {
                removeObserver(this);
                action.run();
            }
        });
    }

    /**
     * Stop observing this object.
     * @param action function to stop running when new data arrives
     */
    public void removeObserver(Runnable action) {
        observers.remove(action);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        User u = (User)obj;
        return profile.sin == u.getProfile().getSin();
    }

    @Override
    public int hashCode() {
        return profile.sin;
    }

    @NonNull
    @Override
    public String toString() {
        return profile.firstName + " " + profile.lastName;
    }

    /** Read-only record of information for this user. */
    public Profile getProfile() {
        return profile;
    }

    /** Database reference to this user. */
    protected DatabaseReference getRef() {
        return ref;
    }

    /** Push the current value of this user's profile to the database. */
    protected void pushToDatabase() {
        ref.setValue(profile);
    }

    public void setFirstName(String firstName) {
        profile.firstName = firstName;
        pushToDatabase();
    }

    public void setLastName(String lastName) {
        profile.lastName = lastName;
        pushToDatabase();
    }

    public void setSin(int sin) {
        profile.sin = sin;
        pushToDatabase();
    }

    public void setGender(String gender) {
        profile.gender = gender;
        pushToDatabase();
    }

    public void setPassword(String password) {
        profile.password = password;
        pushToDatabase();
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        getProfile().appointments = appointments;
        pushToDatabase();
    }

}
