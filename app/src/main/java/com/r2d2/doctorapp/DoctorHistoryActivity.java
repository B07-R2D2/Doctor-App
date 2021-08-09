package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorHistoryActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.DoctorHistoryActivity.extra_patient_profile";

    private DoctorHistory presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_history);

        Intent intent = getIntent();
        Patient.Profile patient = (Patient.Profile) intent.getSerializableExtra(EXTRA_PATIENT_PROFILE);
        presenter = new DoctorHistory(patient, this);

        presenter.fetchDoctors(doctors -> {
            RecyclerView recyclerView = findViewById(R.id.doctor_history_recycler);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, doctors, doctor -> {
                // When row clicked, don't do anything, as we don't have a Patient user
                // to book an appointment for. (Doctors can view this page, too.)
            });
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        });
    }

}
