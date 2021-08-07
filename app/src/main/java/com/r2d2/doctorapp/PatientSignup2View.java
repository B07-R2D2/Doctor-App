package com.r2d2.doctorapp;

import android.content.Intent;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

public class PatientSignup2View {
    private final PatientSignup2Activity view;

    public PatientSignup2View(PatientSignup2Activity view) {
        this.view = view;
    }

    public void backtologin(int SIN, String GEN, String MEDCON)
    {
        Intent backtoLogin = new Intent(view, LoginActivity.class);
        Intent get = view.getIntent();
        String username = get.getStringExtra(PatientSignupActivity.setUSERNAME);
        String password = get.getStringExtra(PatientSignupActivity.setPASSWORD);
        String firstname = get.getStringExtra(PatientSignupActivity.setFIRSTNAME);
        String lastname = get.getStringExtra(PatientSignupActivity.setLASTNAME);

        // makes a new patient that is added to database as the constructor in patient does that automatically
        Patient patient = new Patient(FirebaseDatabase.getInstance(), username);
        patient.setFirstName(firstname);
        patient.setLastName(lastname);
        patient.setPassword(password);
        patient.setGender(GEN);
        patient.setSin(SIN);
        patient.setMedicalCondition(MEDCON);
        //go back to login page to sign in with the newly added user
        view.startActivity(backtoLogin);
    }
    public void back()
    {
        Intent intent = new Intent(view,PatientSignupActivity.class);
        view.startActivity(intent);
    }
}
