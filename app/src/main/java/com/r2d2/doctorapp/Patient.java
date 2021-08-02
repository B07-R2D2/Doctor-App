package com.r2d2.doctorapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.Serializable;
import java.util.Date;

public class Patient extends User implements Serializable {
    /*
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date birthday;
    private int sin;
     */
    private String medicalCondition;
    private int patientID;
    private final DatabaseReference ref;

    public Patient()
    {
        super("","","","",null,0);
        this.medicalCondition = "";
        this.patientID = -1;
        ref = FirebaseDatabase.getInstance().getReference();
    }
    public Patient(String firstName, String lastName, String password, Date birthday, int sin, String medical, String username, int patientId) {
        super(firstName,lastName,username,password,birthday,sin);
        this.medicalCondition = medical;
        this.patientID = patientId;
        //adds this newly initialized patient to the patient database might also add to user database
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

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int PatientId) {
        this.patientID = PatientId;
    }
}

