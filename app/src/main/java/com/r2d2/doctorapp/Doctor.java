package com.r2d2.doctorapp;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Doctor extends User {
    private String bio;
    private String uni;
    private int doctorId;
//    private AvailabilitySchedule availability;
    static HashMap<String, HashSet<Doctor>> specialization = new HashMap<String, HashSet<Doctor>>();

    /* com.r2d2.doctorapp.Doctor class constructor */
    public Doctor(String firstName, String lastName, String username, String password,
                  int sin, String gender,String bio, String uni, int doctorId, String specialization) {
        super(firstName, lastName, username, password, gender, sin);
        this.bio = bio;
        this.uni = uni;
        this.doctorId = doctorId;
        /* initialized static field specialization */
        /* if the key is already in the HashMap, we add the doctor into its value, HashSet */
        /* else we insert an the new key and value into the HashMap*/
        specialization = specialization.toLowerCase();
        if(this.specialization.containsKey(specialization)){
            this.specialization.get(specialization).add(this);
            this.specialization.put(specialization, this.specialization.get(specialization));
        }
        else{
            HashSet<Doctor> d = new HashSet<Doctor>();
            d.add(this);
            this.specialization.put(specialization, d);
        }
    }

    /* To find the available timeslots for the doctor */
//    public AvailabilitySchedule availability(){
//
//    }

    @Override
    public boolean equals(Object obj) {
//        if(obj == null)
//            return false;
//        if(obj.getClass() != this.getClass())
//            return false;
//        com.r2d2.doctorapp.Doctor d = (com.r2d2.doctorapp.Doctor)obj;
//        return this.getSin() == d.getSin();
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
