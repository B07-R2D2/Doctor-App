package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class PatientHomeActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;

    private PatientHome presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Intent intent = getIntent();
        Patient patient = new Patient(FirebaseDatabase.getInstance(), intent.getStringExtra(EXTRA_USERNAME));
        presenter = new PatientHome(patient, this);
    }

    public void findSpecialistButtonClicked(View view) {
        presenter.goToFindSpecialist();
    }

    public void viewProfileButtonClicked(View view) {
        presenter.goToPatientProfile();
    }

}
