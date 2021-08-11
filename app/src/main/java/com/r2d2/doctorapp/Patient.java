package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {

    public static class Profile extends User.Profile {
        private String medicalCondition = "";
        private Long dateOfBirth = System.currentTimeMillis();
        private List<Appointment> pastAppointments = new ArrayList<>();

        public Profile() {
        }

        public Profile(String firstName, String lastName, String username, String password, String gender, int sin, String medicalCondition, long dateOfBirth) {
            super(firstName, lastName, username, password, gender, sin, new ArrayList<>());
            this.medicalCondition = medicalCondition;
            this.dateOfBirth = dateOfBirth;
        }

        public String getMedicalCondition() {
            return medicalCondition;
        }

        public Long getDateOfBirth() {
            return dateOfBirth;
        }

        public List<Appointment> getPastAppointments() {
            return pastAppointments;
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
        super(db.getReference("Patients").child(username), username);
    }

    // constructor for creating a new patient out of a profile
    public Patient(FirebaseDatabase db, Profile profile) {
        super(db.getReference("Patients").child(profile.getUsername()),profile);
    }

//    // constructor for creating a new patient out of a profile
//    public Patient(FirebaseDatabase db, String username, Profile profile) {
//        super(db.getReference("Patients").child(username), username, profile);
//    }

    @NonNull
    @Override
    public String toString()
    {
        return "Patient " + super.toString();
    }

    public void setMedicalCondition(String med)
    {
        getProfile().medicalCondition = med;
        pushToDatabase();
    }

    public void setDate(Long d)
    {
        getProfile().dateOfBirth = d;
        pushToDatabase();
    }

    public void setPastAppointments(List<Appointment> pastAppointments) {
        getProfile().pastAppointments = pastAppointments;
        pushToDatabase();
    }

}
