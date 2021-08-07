package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

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

        public String getMedicalCondition() {
            return medicalCondition;
        }
    }

    @Override
    protected Class<Profile> profileClass() {
        return Profile.class;
    }

    /**
     * Construct a Patient that tracks the patient named {@code username} in the database.
     * @param username username of patient (may or may not exist in database)
     */
    public Patient(String username) {
        super(FirebaseDatabase.getInstance().getReference("Patients").child(username));
        setUsername(username);
    }

    @Override
    public String toString() {
        return "Patient " + super.toString();
    }

    public void setMedicalCondition(String med) {
        ((Profile) getProfile()).medicalCondition = med;
        pushToDatabase();
    }

}

