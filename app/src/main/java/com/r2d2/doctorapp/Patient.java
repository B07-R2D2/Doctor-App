package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;

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
        private long dateOfBirth = System.currentTimeMillis();
        private LinkedList<Appointment> PastAppointList = new LinkedList<>();
        private LinkedList<Appointment> FutureAppointList = new LinkedList<>();
        private LinkedList<String> doctorHistory = new LinkedList<>();

        public String getMedicalCondition() {
            return medicalCondition;
        }
        public Long getDateOfBirth() {return dateOfBirth;}
        public LinkedList<Appointment> getPastAppointments() {return PastAppointList;}
        public LinkedList<Appointment> getFutureAppointment() {return FutureAppointList;}
        public LinkedList<String> getDoctorHistory() { return doctorHistory; }

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
        super(db.getReference("Patients").child(username), username);
    }

    // constructor for creating a new patient out of a profile
    public Patient(FirebaseDatabase db, Profile profile) {
        super(db.getReference("Patients").child(profile.getUsername()),profile);
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

    public void setDate(long d)
    {
        getProfile().dateOfBirth = d;
        pushToDatabase();
    }
    public void addPastAppointment(Appointment p)
    {
        getProfile().getPastAppointments().add(p);
        pushToDatabase();
    }
    public void addDoctor(String d)
    {
        getProfile().doctorHistory.add(d);
        pushToDatabase();
    }
    public void addFutureAppointment(Appointment p)
    {
        getProfile().getFutureAppointment().add(p);
        pushToDatabase();
    }
    public void DeletePastAppointment(Appointment p)
    {
        getProfile().getPastAppointments().remove(p);
        pushToDatabase();
    }
    public void DeleteDoctor(String d)
    {
        getProfile().doctorHistory.remove(d);
        pushToDatabase();
    }
    public void DeleteFutureAppointment(Appointment p)
    {
        getProfile().getFutureAppointment().remove(p);
        pushToDatabase();
    }

    public void setDoctorHistory(LinkedList<String> doctorHistory) {
        getProfile().doctorHistory = doctorHistory;
        pushToDatabase();
    }

}

