package com.r2d2.doctorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorHistoryActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT = "com.r2d2.DoctorApp.DoctorHistoryActivity.extra_patient";
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_history);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra(EXTRA_PATIENT);
    }

    public void findNewSpecialistButtonClicked(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        // TODO: Push patient object as extra
        intent.putExtra("Patient", patient);
        startActivity(intent);
    }

}
