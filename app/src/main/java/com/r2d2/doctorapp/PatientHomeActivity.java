package com.r2d2.doctorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class PatientHomeActivity extends AppCompatActivity {

    public static final String EXTRA_PATIENT = "com.r2d2.DoctorApp.PatientHomeActivity.extra_patient";

    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra(EXTRA_PATIENT);
    }

    public void viewHistoryButtonClicked(View view) {
        Intent intent = new Intent(this, DoctorHistoryActivity.class);
        intent.putExtra(DoctorHistoryActivity.EXTRA_PATIENT, patient);
        startActivity(intent);
    }

    public void viewProfileButtonClicked(View view) {
        Intent intent = new Intent(this, PatientProfileActivity.class);
        intent.putExtra(PatientProfileActivity.EXTRA_PATIENT, patient);
        startActivity(intent);
    }

}

// TODO: Remove stubs once merged with real activity classes.

class PatientProfileActivity extends AppCompatActivity {
    public static final String EXTRA_PATIENT = "com.r2d2.DoctorApp.PatientProfileActivity.extra_patient";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeholder);
    }
}