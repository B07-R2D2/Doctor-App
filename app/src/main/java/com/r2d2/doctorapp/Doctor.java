package com.r2d2.doctorapp;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;



public class Doctor extends User implements Serializable {

    private String bio;
    private String uni;
    private int doctorId;
    private String specialization;

    /* com.r2d2.doctorapp.Doctor class constructor */
    public Doctor(){
        super("","","","",null,0);
        for (int i = 9; i <= 16; i++) {
            super.appointments.add(new Appointment("", "", i));
        }
    }
    public Doctor(String firstName, String lastName, String username, String password,
                  int sin, String gender,String bio, String uni, int doctorId, String specialization) {
        super(firstName, lastName, username, password, gender, sin);
        this.bio = bio;
        this.uni = uni;
        this.doctorId = doctorId;
        this.specialization = specialization;
        for (int i = 9; i <= 16; i++) {
            super.appointments.add(new Appointment(username, "", i));
        }
    }

    // Don't need to extract from database bc the doctor should be newly updated already ( I think, might change in future )
    // returns a list of free appointments (ie. patientName = "")
    ArrayList<Appointment> getFreeAppointments(){
        ArrayList<Appointment> freeAppointments = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            if (super.appointments.get(i).getPatientName().equals("")) {
                freeAppointments.add(super.appointments.get(i));
            }
        }
        return freeAppointments;
    }

    // returns a list of appointments (ie. patientName != "") for user story 4
    ArrayList<Appointment> getBookedAppointments() {
        ArrayList<Appointment> bookedAppointments = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            if (!super.appointments.get(i).getPatientName().equals("")) {
                bookedAppointments.add(super.appointments.get(i));
            }
        }
        return bookedAppointments;
    }

    // returns the next Appointment (ie. getAppointmentWithMinTimeSlot(getAppointments()) for user story 3
    // or null if don't have next appointment
    Appointment getNextAppointment() {
        Appointment next = null;
        for (Appointment app : this.getBookedAppointments()) {
            next = new Appointment(app.getDoctorName(), app.getPatientName(), app.getTimeSlot());
            break;
        }
        return next;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
