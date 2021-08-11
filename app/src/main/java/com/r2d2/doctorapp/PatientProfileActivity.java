package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PatientProfileActivity extends AppCompatActivity {

    /** Value may be null! */
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.PatientProfileActivity.extra_patient_profile";

    private PatientProfile patientProfile;
    private AppointmentHistory appointmentHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        Intent intent = getIntent();
        Patient.Profile profile = (Patient.Profile) intent.getSerializableExtra(EXTRA_PATIENT_PROFILE);
        patientProfile = new PatientProfile(profile, this);
        appointmentHistory = new AppointmentHistory(profile, this);

        TextView fullName = (TextView) findViewById(R.id.patient_profile_full_name),
                 gender = (TextView) findViewById(R.id.patient_profile_gender),
                 birthdate = (TextView) findViewById(R.id.patient_profile_birthdate),
                 medicalCondition = (TextView) findViewById(R.id.patient_profile_medical_condition);
        fullName.setText(patientProfile.getFullName());
        gender.setText(patientProfile.getGender());
        birthdate.setText(patientProfile.getDateOfBirth());
        medicalCondition.setText(patientProfile.getMedicalCondition());

        RecyclerView recyclerView = findViewById(R.id.appointment_history_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        appointmentHistory.fetchAppointmentInfo(info -> {
            recyclerView.setAdapter(new PlainTextRecyclerAdapter(info));
        });
    }

}