package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends User {

    public static class Profile extends User.Profile {
        private String bio = "";
        private String uni = "";
        private int doctorId;
        private String specialization = "";

        public Profile() {
        }

        public Profile(String firstName, String lastName, String username, String password, String gender, int sin, ArrayList<Appointment> appointments, String bio, String uni, int doctorId, String specialization) {
            super(firstName, lastName, username, password, gender, sin, appointments);
            this.bio = bio;
            this.uni = uni;
            this.doctorId = doctorId;
            this.specialization = specialization;
        }

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

// comment out these bc we don't need them and they cause errors (not sure if we'll use them in the future tho)
/*
        public ArrayList<Appointment> getFreeAppointments(){
            ArrayList<Appointment> freeAppointments = new ArrayList<>();
            for (int i = 0; i < 7; i++) {           // this is the day in week
                for (int j = 0; j < 8; j++) {       // timeslot in day
                    if (appointments.get(i * 8 + j).getPatientName().equals("")) {
                        freeAppointments.add(appointments.get(i * 8 + j));
                    }
                }
            }
            return freeAppointments;
        }

        // returns a list of appointments (ie. patientName != "") for user story 4
        public ArrayList<Appointment> getBookedAppointments() {
            ArrayList<Appointment> bookedAppointments = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!appointments.get(i * 8 + j).getPatientName().equals("")) {
                        bookedAppointments.add(appointments.get(i * 8 + j));
                    }
                }
            }
            return bookedAppointments;
        }
        */

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
     * Construct a Doctor that tracks the doctor named {@code username} in the database.
     * @param username username of doctor (may or may not exist in database)
     */
    public Doctor(FirebaseDatabase db, String username) {
        super(db.getReference("Doctors").child(username), username);
    }

    // constructor for creating a new doctor out of a profile
    public Doctor(FirebaseDatabase db, Profile profile) {
        super(db.getReference("Doctors").child(profile.getUsername()), profile);
    }

    @NonNull
    @Override
    public String toString() {
        return "Dr. " + super.toString();
    }

    @Override
    protected void pushToDatabase() {
        super.pushToDatabase();
        Profile profile = getProfile();;
        Log.i("Doctor", "Pushing " + profile.getFirstName() + " " + profile.getLastName() + " " + profile.getSpecialization() + " " + profile.getGender());
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

    public static ArrayList<Appointment> makeOneWeekOfAppointments(String doctorUsername) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Date d = new Date();
        d.setHours(9);
        d.setMinutes(0);
        d.setSeconds(0);
        Instant ori = d.toInstant();
        for (int i = 0; i < 7; i++) {                           // 7 days in a week
            for (int j = 0; j < 8; j++) {                       // 8 timeslots a
                // can't book for today (bc time rn might be after booking time
                Instant cur = ori.plus(i + 1, ChronoUnit.DAYS);
                cur = cur.plus(j, ChronoUnit.HOURS);
                Appointment app = new Appointment(doctorUsername, "", cur.getEpochSecond());
                appointments.add(app);
            }
        }
        return appointments;
    }

}