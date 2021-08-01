package com.r2d2.doctorapp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Patient extends User{
    private String medicalCondition;
    private String username;
    private int patientID;
    //private schedule patientSchedule;
    private final DatabaseReference ref;

    public Patient(String firstName, String lastName, Date birthday, int sin, String medical, String username, int patientId) {
        super(firstName,lastName,birthday,sin);
        this.medicalCondition = medical;
        this.username = username;
        this.patientID = patientId;
        //adds this newly intialized patient to the patient database might also add to user database
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("User").child(username).setValue(this);
        ref.child("Patient").child(username).setValue(this);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Patient " + super.toString();
    }

    /* Getters and Setters for all private variables. */
    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String med) {
        this.medicalCondition = med;
    }

    public int getpatientID() {
        return patientID;
    }

    public void setpatientID(int PatientId) {
        this.patientID = PatientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

