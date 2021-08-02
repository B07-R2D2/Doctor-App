package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PatientSignup2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup2);
    }
    public void sendInfo(View view) {
        // Do something in response to button
        /*
        Intent intent = new Intent(PatientSignupActivity.this, PatientSignup2Activity.class);
        EditText send = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText send2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText send3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText send4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        String setUsername = send.getText().toString();
        String setPassword = send2.getText().toString();
        String setFirstName = send3.getText().toString();
        String setLastName = send4.getText().toString();
        intent.putExtra(setUsername, setUSERNAME);
        intent.putExtra(setPassword, setPASSWORD);
        intent.putExtra(setFirstName, setFIRSTNAME);
        intent.putExtra(setLastName, setLASTNAME);
        startActivity(intent);
         */
    }
    public void back(View view)
    {
        Intent intent = new Intent(this,PatientSignupActivity.class);
        startActivity(intent);
    }
}