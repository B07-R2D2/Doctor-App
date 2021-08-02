package com.r2d2.doctorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorHistoryActivity extends AppCompatActivity {

    public static final String EXTRA_PATIENT = "com.r2d2.DoctorApp.DoctorHistoryActivity.extra_patient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_history);
    }

    public void findNewSpecialistButtonClicked(View view) {
        Intent intent = new Intent(this, FilterActivity.class);
        // TODO: Push patient object as extra
        startActivity(intent);
    }

}

// TODO: Remove stubs once merged with real activity classes.

class FilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeholder);
    }
}