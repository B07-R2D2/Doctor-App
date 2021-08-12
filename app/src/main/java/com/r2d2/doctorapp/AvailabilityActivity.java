
package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Note:  AvailablilitySchedule will need to implement Serializable (to be passed into this Activity)
//        DateTimeInterval will need to implement Comparable<DateTimeInterval> (hashset -> ArrayList -> sort and display)
public class AvailabilityActivity extends AppCompatActivity {
    private Doctor.Profile doctor;
    private Patient.Profile patient;
    private RecyclerView recyclerView;

    public static final String EXTRA_DOCTOR_PROFILE = "com.r2d2.DoctorApp.AvailabilityActivity.extra_doctor_profile";
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.AvailabilityActivity.extra_patient_profile";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        recyclerView = findViewById(R.id.availability_recycler_view);   // set the recycler view

        // after the patient click the doctor, we will receive an intent, which includes a schedule
        Intent intent = getIntent();
        doctor = (Doctor.Profile) intent.getSerializableExtra(EXTRA_DOCTOR_PROFILE);
        patient = (Patient.Profile) intent.getSerializableExtra(EXTRA_PATIENT_PROFILE);

        setAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setAdapter() {
        if (doctor.getAppointments() == null) return;

        // the adapter takes in a schedule and displays the contents
        RecyclerAdapter adapter = new RecyclerAdapter(doctor, patient);

        // sets the layout, default animator, and adapter of recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


}