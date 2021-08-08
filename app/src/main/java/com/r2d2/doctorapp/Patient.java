package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        private Date DateOfBirth = new Date();
        private LinkedList<Doctor> DocList = new LinkedList<Doctor>();
        //private LinkedList<Appointment> PastAppointList = new LinkedList<Appointment>();
        //private LinkedList<Appointment> FutureAppointList = new LinkedList<Appointment>();

        public String getMedicalCondition() {
            return medicalCondition;
        }
        public Date getDateOfBirth() {return DateOfBirth;}
        //public LinkedList<Appointment> getPastAppointment() {return PastAppointList;}
        public LinkedList<Doctor> getDoctorList() {return DocList;}
        //public LinkedList<Appointment> getFutureAppointment() {return FutureAppointList;}
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
    /*
    public void addPastAppointment(Appointment p)
    {
        getProfile().getPastAppointment().add(p);
    }
    public void addDoctor(Doctor d)
    {
        getProfile().getDoctorList().add(d);
    }
    public void addFutureAppointment(Appointment p)
    {
        getProfile().getFutureAppointment().add(p);
    }
    public void DeletePastAppointment(Appointment p)
    {
        getProfile().getPastAppointment().remove(p);
    }
    public void DeleteDoctor(Doctor d)
    {
        getProfile().getDoctorList().remove(d);
    }
    public void DeleteFutureAppointment(Appointment p)
    {
        getProfile().getFutureAppointment().remove(p);
    }
    */
}

