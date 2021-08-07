package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

/** A user of the app. Subclasses may add additional fields with a subclass of {@code Profile}. */
public abstract class User {

    /** A read-only record of user information. */
    public static class Profile implements Serializable {
        private String firstName = "";
        private String lastName = "";
        private String userName = "";
        private String password = "";
        private String gender = "Other";
        private int sin;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return userName;
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
    public User(DatabaseReference ref) {
        this.ref = ref;
        this.profile = newProfile();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Profile newProfile = snapshot.getValue(profileClass());
                if (newProfile != null)
                    profile = newProfile;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("User onCancelled", error.toException());
            }
        });
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
        return profile.firstName + ", " + profile.lastName;
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
    
    public void setUsername(String userName) {
        profile.userName = userName;
        pushToDatabase();
    }
    
    public void setPassword(String password) {
        profile.password = password;
        pushToDatabase();
    }
}
