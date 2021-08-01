package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
    }

    public void viewHistoryButtonClicked(View view) {
        Intent intent = new Intent(this, DoctorHistoryActivity.class);
        // TODO: Push patient object as extra
        startActivity(intent);
    }

    public void viewProfileButtonClicked(View view) {
        Intent intent = new Intent(this, PatientProfileActivity.class);
        // TODO: Push patient object as extra
        startActivity(intent);
    }

}

// TODO: Remove stubs once merged with real activity classes.

class PatientProfileActivity extends AppCompatActivity {
}