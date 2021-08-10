package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends User {

    public static class Profile extends User.Profile {

        private String medicalCondition = "";
        private Date DateOfBirth = new Date();
        private List<Appointment> pastAppointments = new ArrayList<>();

        public Profile() {
        }

        public String getMedicalCondition() {
            return medicalCondition;
        }

        public Date getDateOfBirth() {return DateOfBirth;}

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
        super(db.getReference("Patients").child(profile.getUsername()), profile);
    }

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

    public void setDate(Date d)
    {
        getProfile().DateOfBirth = d;
        pushToDatabase();
    }

    public void setPastAppointments(List<Appointment> pastAppointments) {
        getProfile().pastAppointments = pastAppointments;
        pushToDatabase();
    }

}

