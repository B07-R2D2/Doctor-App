package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PatientSignupActivity extends AppCompatActivity {
    public static final String setUSERNAME = "com.example.DoctorApp.SETUSERMESSAGE";
    public static final String setPASSWORD = "com.example.DoctorApp.SETPASSWORDMESSAGE";
    public static final String setFIRSTNAME = "com.example.DoctorApp.SETFIRSTNAMEMESSAGE";
    public static final String setLASTNAME = "com.example.DoctorApp.SETLASTNAMEMESSAGE";
    private PatientSignupView presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PatientSignupView(this);
        setContentView(R.layout.activity_patientsignup);
    }
    public void sendInfo(View view) {
        // sends username,password, first name and last name to the next activity
        EditText send = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText send2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText send3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText send4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        String setUsername = send2.getText().toString().trim();
        String setPassword = send.getText().toString().trim();
        String setFirstName = send3.getText().toString().trim();
        String setLastName = send4.getText().toString().trim();
        if(setUsername.isEmpty()){
            send2.setError("Please Enter Username");
            send2.requestFocus();
            return;
        }
        if(setPassword.isEmpty()){
            send.setError("Please Enter Password");
            send.requestFocus();
            return;
        }
        if(setFirstName.isEmpty()){
            send3.setError("Please Enter your First name");
            send3.requestFocus();
            return;
        }
        if(setLastName.isEmpty()){
            send4.setError("Please Enter your last name");
            send4.requestFocus();
            return;
        }
        presenter.sendInfo(setUsername,setPassword,setFirstName,setLastName);
    }
    public void back(View view)
    {
        presenter.back();
    }
}