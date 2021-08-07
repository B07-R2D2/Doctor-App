package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PatientHomeActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;

    private PatientHome presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Intent intent = getIntent();
        presenter = new PatientHome(new Patient(intent.getStringExtra(EXTRA_USERNAME)), this);
    }

    public void viewHistoryButtonClicked(View view) {
        presenter.goToDoctorHistory();
    }

    public void viewProfileButtonClicked(View view) {
        presenter.goToPatientProfile();
    }

}
