package com.r2d2.doctorapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Doctor extends User {

    public class Profile extends User.Profile {
        private String bio;
        private String uni;
        private int doctorId;
        private String specialization;

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

    private AvailabilitySchedule availability;

    /**
     * Construct a Doctor that tracks the doctor named {@code username} in the database.
     * @param username username of doctor (may or may not exist in database)
     */
    public Doctor(String username) {
        super(FirebaseDatabase.getInstance().getReference("Doctors").child(username));
        setUsername(username);
    }

    /* To find the available timeslots for the doctor */
    public AvailabilitySchedule availability() {
        return new AvailabilitySchedule(getRef(), GregorianCalendar.getInstance());
    }

    @Override
    public String toString() {
        return "Dr. " + super.toString();
    }

    @Override
    protected void pushToDatabase() {
        super.pushToDatabase();
        Profile profile = (Profile) getProfile();
        FirebaseDatabase.getInstance()
                .getReference("DoctorsSpecial")
                .child(profile.getSpecialization().toLowerCase())
                .child(profile.getUsername())
                .setValue(profile);
    }

    public void setBio(String bio) {
        ((Profile) getProfile()).bio = bio;
        pushToDatabase();
    }

    public void setUni(String uni) {
        ((Profile) getProfile()).uni = uni;
        pushToDatabase();
    }

    public void setDoctorId(int doctorId) {
        ((Profile) getProfile()).doctorId = doctorId;
        pushToDatabase();
    }

    public void setSpecialization(String specialization) {
        ((Profile) getProfile()).specialization = specialization;
        pushToDatabase();
    }

}
