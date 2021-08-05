package com.r2d2.doctorapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PatientProfileActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT = "com.r2d2.DoctorApp.PatientProfileActivity.extra_patient";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeholder);
    }

}