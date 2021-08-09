package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentHistoryActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.DoctorHistoryActivity.extra_patient_profile";

    private AppointmentHistory presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        Intent intent = getIntent();
        Patient.Profile patient = (Patient.Profile) intent.getSerializableExtra(EXTRA_PATIENT_PROFILE);
        presenter = new AppointmentHistory(patient, this);

        recyclerView = findViewById(R.id.doctor_history_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.fetchAppointmentInfo(info -> {
            recyclerView.setAdapter(new PlainTextRecyclerAdapter(info));
        });
    }

}
