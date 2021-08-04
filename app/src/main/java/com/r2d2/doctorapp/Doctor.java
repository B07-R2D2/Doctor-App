package com.r2d2.doctorapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.GregorianCalendar;



public class Doctor extends User implements Serializable {

    private String bio;
    private String uni;
    private int doctorId;
    private AvailabilitySchedule availability;
    private String specialization;

    /* com.r2d2.doctorapp.Doctor class constructor */
    public Doctor(){
        super("","","","",null,0);
    }
    public Doctor(String firstName, String lastName, String username, String password,
                  int sin, String gender,String bio, String uni, int doctorId, String specialization) {
        super(firstName, lastName, username, password, gender, sin);
        this.bio = bio;
        this.uni = uni;
        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    /* To find the available timeslots for the doctor */
    public AvailabilitySchedule availability(){
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference().child("Doctors").child(this.getUsername());
        return new AvailabilitySchedule(doctorRef, GregorianCalendar.getInstance());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
        /* return doctorId; */
    }

    @Override
    public String toString() {
        return "Dr. " + super.toString();
    }

    /* Getters and Setters for all private variables. */
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}