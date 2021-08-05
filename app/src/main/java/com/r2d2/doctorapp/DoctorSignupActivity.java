package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

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
        String username = editText1.getText().toString().trim();
        String password = editText2.getText().toString().trim();
        String firstname = editText3.getText().toString().trim();
        String lastname = editText4.getText().toString().trim();
        String gender = editText5.getText().toString().trim().toLowerCase();

        Pattern pattern = Pattern.compile("\\d+");
        Pattern genderpattern = Pattern.compile("(male|female|other)");
        if(username.isEmpty()){
            editText1.setError("Please Enter Username");
            editText1.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editText2.setError("Please Enter Password");
            editText2.requestFocus();
            return;
        }
        if(firstname.isEmpty()){
            editText3.setError("Please Enter your First name");
            editText3.requestFocus();
            return;
        }
        if(lastname.isEmpty()){
            editText4.setError("Please Enter your last name");
            editText4.requestFocus();
            return;
        }
        if(gender.isEmpty()){
            editText5.setError("Please Enter your Gender");
            editText5.requestFocus();
            return;
        }
        else if(genderpattern.matcher(gender).matches() == false){
            editText5.setError("Please enter Male, Female, or Other");
            editText5.requestFocus();
            return;
        }

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