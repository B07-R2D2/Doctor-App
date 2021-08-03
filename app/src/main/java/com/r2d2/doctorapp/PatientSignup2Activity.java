package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PatientSignup2Activity extends AppCompatActivity {
    public static final String setMEDICALCONDITION = "com.example.DoctorApp.MEDICALCONDITION";
    public static final String setGENDER = "com.example.DoctorApp.GENDER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup2);
    }

    public void signup(View view) {
        // Do something in response to button
        Intent backtoLog = new Intent(this, LoginActivity.class);
        EditText send = (EditText) findViewById(R.id.editTextTextMultiLine);
        EditText send2 = (EditText) findViewById(R.id.gender);
        EditText send3 = (EditText) findViewById(R.id.sinNumber);
        String MEDCON = send.getText().toString();
        String GEN = send2.getText().toString();
        String temp = send3.getText().toString();
        int SIN = Integer.parseInt(temp);
        Intent get = getIntent();
        String username = get.getStringExtra(PatientSignupActivity.setUSERNAME);
        String password = get.getStringExtra(PatientSignupActivity.setPASSWORD);
        String firstname = get.getStringExtra(PatientSignupActivity.setFIRSTNAME);
        String lastname = get.getStringExtra(PatientSignupActivity.setLASTNAME);
        // makes a new patient that is added to database as the constructor in patient does that automatically
        Patient current = new Patient(
                firstname,
                lastname,
                username,
                password,
                GEN,
                SIN,
                MEDCON
                );
        //go back to login page to sign in with the newly added user
        startActivity(backtoLog);
    }
    public void back(View view)
    {
        Intent intent = new Intent(this,PatientSignupActivity.class);
        startActivity(intent);
    }
}