package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Patient extends User {

    public static class Profile extends User.Profile {
        /*
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private String gender;
        private int sin;
         */

        private String medicalCondition = "";

        public String getMedicalCondition() {
            return medicalCondition;
        }

        // initialize appointments
        public Profile() {
            super.appointments = new ArrayList<>();
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

    /**
     * Construct a Patient that tracks the patient named {@code username} in the database.
     * @param username username of patient (may or may not exist in database)
     */
    public Patient(FirebaseDatabase db, String username) {
        super(db.getReference("patients").child(username), username);
    }

    // constructor for creating a new patient out of a profile
    public Patient(FirebaseDatabase db, String username, Profile profile) {
        super(db.getReference("patients").child(username), username, profile);
    }

    @NonNull
    @Override
    public String toString() {
        return "Patient " + super.toString();
    }

    public void setMedicalCondition(String med) {
        getProfile().medicalCondition = med;
        pushToDatabase();
    }

}
