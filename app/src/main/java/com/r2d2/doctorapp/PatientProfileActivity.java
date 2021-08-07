package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PatientProfileActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.PatientProfileActivity.extra_patient_profile";

    private PatientProfile presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        Intent intent = getIntent();
        Patient.Profile patientProfile = (Patient.Profile) intent.getSerializableExtra(EXTRA_PATIENT_PROFILE);
        presenter = new PatientProfile(patientProfile, this);

        TextView fullName = (TextView) findViewById(R.id.patient_profile_full_name),
                 gender = (TextView) findViewById(R.id.patient_profile_gender),
                 medicalCondition = (TextView) findViewById(R.id.patient_profile_medical_condition);
        fullName.setText(presenter.getFullName());
        gender.setText(presenter.getGender());
        medicalCondition.setText(presenter.getMedicalCondition());
    }

    public void viewHistoryButtonClicked(View view) {
        presenter.goToDoctorHistory();
    }

}