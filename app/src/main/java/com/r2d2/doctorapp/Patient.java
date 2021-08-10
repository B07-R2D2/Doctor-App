package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Patient extends User {

    public static class Profile extends User.Profile {

        private String medicalCondition = "";
        private Long DateOfBirth = System.currentTimeMillis();
        private LinkedList<Appointment> PastAppointList = new LinkedList<Appointment>();
        private LinkedList<Appointment> FutureAppointList = new LinkedList<Appointment>();
        private LinkedList<String> doctorHistory = new LinkedList<String>();

        public String getMedicalCondition() {
            return medicalCondition;
        }

        public Long getDateOfBirth() {return DateOfBirth;}
        public LinkedList<Appointment> getPastAppointment() {return PastAppointList;}
        public LinkedList<Appointment> getFutureAppointment() {return FutureAppointList;}
        public LinkedList<String> getDoctorHistory() { return doctorHistory; }

        // initialize appointments
        public Profile() {
            super.appointments = new ArrayList<>();

        public long getDateOfBirth() {
            return DateOfBirth;
        }

        public List<Appointment> getPastAppointments() {
            return PastAppointList;
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
