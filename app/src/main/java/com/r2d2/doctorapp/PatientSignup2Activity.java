package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientSignup2Activity extends AppCompatActivity {
    public final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup2);
    }

    public void signup(View view) {
        // Do something in response to clicking Submit
        Intent backtoLogin = new Intent(this, LoginActivity.class);
        EditText send = (EditText) findViewById(R.id.editTextTextMultiLine);
        EditText send2 = (EditText) findViewById(R.id.gender);
        EditText send3 = (EditText) findViewById(R.id.sinNumber);
        String MEDCON = send.getText().toString();
        String GEN = send2.getText().toString();
        String temp = send3.getText().toString();
        int SIN = 0;
        try {
            SIN = Integer.parseInt(temp);
        }
        catch (NumberFormatException error)
        {
            System.out.println("Could not parse " + error);
        }
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
        //adds new user to the patients
        ref.child("Patients").child(username).setValue(current);
        //go back to login page to sign in with the newly added user
        startActivity(backtoLogin);
    }
    public void back(View view)
    {
        Intent intent = new Intent(this,PatientSignupActivity.class);
        startActivity(intent);
    }
}