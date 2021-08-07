package com.r2d2.doctorapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends User implements Serializable {
    /*
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private int sin;
     */
    private String medicalCondition;

    public Patient()
    {
        super("","","","",null,0);
        this.medicalCondition = "";
    }

    public Patient(String firstName, String lastName,String username, String password, String gender, int sin, String medical) {
        super(firstName,lastName,username,password,gender,sin);
        this.medicalCondition = medical;
        //adds this newly initialized patient to the patient database
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
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

}

