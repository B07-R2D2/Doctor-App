package com.r2d2.doctorapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorHistoryActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.DoctorHistoryActivity.extra_patient_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_history);
    }

}
