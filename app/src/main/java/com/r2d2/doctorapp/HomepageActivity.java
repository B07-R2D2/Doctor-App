package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void simulatePatientLoginClicked(View view) {
        Intent intent = new Intent(this, PatientHomeActivity.class);
        Patient simulatedPatient = new Patient(
                "Example",
                "One",
                "password123",
                "Male",
                123456789,
                "Broken wrist",
                "example1",
                1
        );
        intent.putExtra(PatientHomeActivity.EXTRA_PATIENT, simulatedPatient);
        startActivity(intent);
    }

}