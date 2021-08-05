package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class DoctorSignup2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup2);
    }

    public void submitButton(View view){
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
        String uni = editText1.getText().toString().trim();
        String specialization = editText2.getText().toString().trim();
        String dId = editText3.getText().toString().trim();
        String sin1 = editText4.getText().toString().trim();
        String bio = editText5.getText().toString().trim();

        Pattern pattern = Pattern.compile("\\d+");
        if(uni.isEmpty()){
            editText1.setError("Please Enter where you attend university");
            editText1.requestFocus();
            return;
        }
        if(specialization.isEmpty()){
            editText2.setError("Please Enter your specialization");
            editText2.requestFocus();
            return;
        }
        if(dId.isEmpty()){
            editText3.setError("Please Enter your Doctor's Id Number");
            editText3.requestFocus();
            return;
        }
        else if(pattern.matcher(dId).matches() == false){
            editText3.setError("Doctor's Id requires all digits");
            editText3.requestFocus();
            return;
        }
        if(sin1.isEmpty()){
            editText4.setError("Please Enter your SIN Number");
            editText4.requestFocus();
            return;
        }
        else if(pattern.matcher(sin1).matches() == false){
            editText4.setError("SIN Number requires all digits");
            editText4.requestFocus();
            return;
        }
        if(bio.isEmpty()){
            editText5.setError("Please Enter a a short bio about yourself");
            editText5.requestFocus();
            return;
        }

        int doctorId = new Integer(dId).intValue();
        int sin = new Integer(sin1).intValue();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Doctor d = new Doctor(firstname, lastname, username, password, sin, gender, bio, uni, doctorId,specialization);
        ref.child("Doctors").child(username).setValue(d);
        ref.child("DoctorsSpecial").child(specialization.toLowerCase()).child(username).setValue(d);

        //Send user back to log in page.
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void backButton1(View view){
        Intent intent = new Intent(this,DoctorSignupActivity.class);
        startActivity(intent);
    }
}