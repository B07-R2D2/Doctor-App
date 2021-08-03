package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorSignup2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup2);
        Intent intent = getIntent();
        String username = intent.getStringExtra(DoctorSignupActivity.setUSERNAME);
    }

    public void submitButton(View view){
//        Intent intent = new Intent(DoctorSignupActivity.this,.class); where should we go after submitted?
        Intent da1 = getIntent();
        String username = da1.getStringExtra(DoctorSignupActivity.setUSERNAME);
        String password = da1.getStringExtra(DoctorSignupActivity.setPASSWORD);
        String firstname = da1.getStringExtra(DoctorSignupActivity.setFIRSTNAME);
        String lastname = da1.getStringExtra(DoctorSignupActivity.setLASTNAME);
        String gender = da1.getStringExtra(DoctorSignupActivity.setGENDER);

        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText editText3 = (EditText) findViewById(R.id.editTextTextPersonName8);
        EditText editText4 = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText5 = (EditText) findViewById(R.id.editTextTextPersonName10);
        String uni = editText1.getText().toString();
        String specialization = editText2.getText().toString();
        String dId = editText3.getText().toString();
        int doctorId = new Integer(dId).intValue();
        String sin1 = editText4.getText().toString();
        int sin = new Integer(sin1).intValue();
        String bio = editText5.getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Doctor d = new Doctor(firstname, lastname, username, password, sin, gender, bio, uni, doctorId,specialization);
//        ref.child("doctor").setValue(d);
        ref.child("specialization").child(specialization.toLowerCase()).setValue(d);
    }

    public void backButton1(View view){
        Intent intent = new Intent(this,DoctorSignupActivity.class);
        startActivity(intent);
    }
}