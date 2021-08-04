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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientsignup);
    }
    public void sendInfo(View view) {
        // sends username,password, first name and last name to the next activity
        Intent intent = new Intent(PatientSignupActivity.this, PatientSignup2Activity.class);
        EditText send = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText send2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText send3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText send4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        String setUsername = send2.getText().toString();
        String setPassword = send3.getText().toString();
        String setFirstName = send3.getText().toString();
        String setLastName = send4.getText().toString();
        intent.putExtra(setUSERNAME, setUsername);
        intent.putExtra(setPASSWORD, setPassword);
        intent.putExtra(setFIRSTNAME, setFirstName);
        intent.putExtra(setLASTNAME, setLastName);
        startActivity(intent);
    }
    public void back(View view)
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}