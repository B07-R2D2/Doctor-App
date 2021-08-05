package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorHomeActivity extends AppCompatActivity {
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
    }

//    Jump to doctor profile ( we'll prob do this in user stories )
//    public void jumpToDoctorProfile(View view){
//        Intent intent = new Intent(this, DoctorHomeActivity.class);
//        startActivity(intent);
//    }


}