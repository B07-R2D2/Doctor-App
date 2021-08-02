package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PatientSignup2Activity extends AppCompatActivity {
    public static final String setMEDICALCONDITION = "com.example.DoctorApp.MEDICALCONDITIONMESSAGE";
    public static final String setGENDER = "com.example.DoctorApp.GENDERMESSAGE";
    public static final String username  = "com.example.DoctorApp.MESSAGE";
    public static final String password  = "com.example.DoctorApp.MESSAGE";
    public static final String firstname  = "com.example.DoctorApp.MESSAGE";
    public static final String lastname  = "com.example.DoctorApp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup2);
        Intent get = getIntent();
        String username = get.getStringExtra(PatientSignupActivity.setUSERNAME);
        String password = get.getStringExtra(PatientSignupActivity.setPASSWORD);
        String firstname = get.getStringExtra(PatientSignupActivity.setFIRSTNAME);
        String lastname = get.getStringExtra(PatientSignupActivity.setLASTNAME);
    }
    public void sendInfo(View view) {
        // Do something in response to button

        Intent intent = new Intent(PatientSignup2Activity.this, LoginActivity.class);
        //EditText send = (EditText) findViewById(R.id.editTextTextPersonName);
        //EditText send2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        //EditText send3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        //String setUsername = send.getText().toString();
        //String setPassword = send2.getText().toString();
        //String setFirstName = send3.getText().toString();
        //intent.putExtra(setUsername, setUSERNAME);
        //intent.putExtra(setPassword, setPASSWORD);
        //intent.putExtra(setFirstName, setFIRSTNAME);
        int SIN = 0;
        Patient current = new Patient(
                PatientSignupActivity.setFIRSTNAME,
                PatientSignupActivity.setLASTNAME,
                PatientSignupActivity.setUSERNAME,
                PatientSignupActivity.setPASSWORD,
                setGENDER,
                SIN,
                setMEDICALCONDITION
                );

        startActivity(intent);

    }
    public void back(View view)
    {
        Intent intent = new Intent(this,PatientSignupActivity.class);
        startActivity(intent);
    }
}