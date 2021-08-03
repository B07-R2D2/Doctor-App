package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DoctorHomeActivity extends AppCompatActivity {
    Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
    }
}