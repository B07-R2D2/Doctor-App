package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DoctorSignupActivity extends AppCompatActivity {
    public static final String setUSERNAME = "com.example.DoctorApp.SETUSERMESSAGE";
    public static final String setPASSWORD = "com.example.DoctorApp.SETPASSWORDMESSAGE";
    public static final String setFIRSTNAME = "com.example.DoctorApp.SETFIRSTNAMEMESSAGE";
    public static final String setLASTNAME = "com.example.DoctorApp.SETLASTNAMEMESSAGE";
    public static final String setGENDER = "com.example.DoctorApp.SETGENDERMESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);
    }
    public void sendMessage(View view){
        Intent intent = new Intent(DoctorSignupActivity.this, DoctorSignup2Activity.class);
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText editText4 = (EditText) findViewById(R.id.editTextTextPersonName7);
        EditText editText5 = (EditText) findViewById(R.id.editTextTextPersonName9);
        String username = editText1.getText().toString();
        String password = editText2.getText().toString();
        String firstname = editText3.getText().toString();
        String lastname = editText4.getText().toString();
        String gender = editText5.getText().toString();
        intent.putExtra(setUSERNAME, username);
        intent.putExtra(setPASSWORD, password);
        intent.putExtra(setFIRSTNAME, firstname);
        intent.putExtra(setLASTNAME, lastname);
        intent.putExtra(setGENDER, gender);
        startActivity(intent);
    }
    public void backButton(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}