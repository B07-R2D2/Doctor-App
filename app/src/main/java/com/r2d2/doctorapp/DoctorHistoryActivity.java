package com.r2d2.doctorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorHistoryActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.DoctorHistoryActivity.extra_patient_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_history);
    }

    public void findNewSpecialistButtonClicked(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        // TODO: Push patient profile as extra
        startActivity(intent);
    }

}
