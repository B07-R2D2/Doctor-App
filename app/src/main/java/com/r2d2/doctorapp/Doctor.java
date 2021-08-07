package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.GregorianCalendar;

public class Doctor extends User {

    public static class Profile extends User.Profile {
        private String bio = "";
        private String uni = "";
        private int doctorId;
        private String specialization = "";

        public String getBio() {
            return bio;
        }

        public String getUni() {
            return uni;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public String getSpecialization() {
            return specialization;
        }
    }

    @Override
    protected Class<Profile> profileClass() {
        return Profile.class;
    }

    @Override
    protected Profile newProfile() {
        return new Profile();
    }

    @Override
    public Profile getProfile() {
        return (Profile) super.getProfile();
    }

    private AvailabilitySchedule availability;

    /**
     * Construct a Doctor that tracks the doctor named {@code username} in the database.
     * @param username username of doctor (may or may not exist in database)
     */
    public Doctor(FirebaseDatabase db, String username) {
        super(db.getReference("Doctors").child(username), username);
    }

    /* To find the available timeslots for the doctor */
    public AvailabilitySchedule availability() {
        return new AvailabilitySchedule(getRef(), GregorianCalendar.getInstance());
    }

    @NonNull
    @Override
    public String toString() {
        return "Dr. " + super.toString();
    }

    @Override
    protected void pushToDatabase() {
        super.pushToDatabase();
        Profile profile = getProfile();
        if (!profile.getSpecialization().equals("")) {
            getRef().getDatabase()
                    .getReference("DoctorsSpecial")
                    .child(profile.getSpecialization().toLowerCase())
                    .child(profile.getUsername())
                    .setValue(profile);
        }
    }

    public void setBio(String bio) {
        getProfile().bio = bio;
        pushToDatabase();
    }

    public void setUni(String uni) {
        getProfile().uni = uni;
        pushToDatabase();
    }

    public void setDoctorId(int doctorId) {
        getProfile().doctorId = doctorId;
        pushToDatabase();
    }

    public void setSpecialization(String specialization) {
        getProfile().specialization = specialization;
        pushToDatabase();
    }

}
