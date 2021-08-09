package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ExActivity extends AppCompatActivity {

    public static final String EXTRA_DOCTOR_PROFILE = "com.r2d2.DoctorApp.ExActivity.extra_doctor_profile";
    public static final String EXTRA_PATIENT_PROFILE = "com.r2d2.DoctorApp.ExActivity.extra_patient_profile";
    public static final String EXTRA_SPECFILTER = "com.r2d2.DoctorApp.ExActivity.extra_specfilter";

    private TextView name;
    private TextView gender;
    private TextView specialization;
    private TextView bio;
    private TextView patientStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

        name = findViewById(R.id.exName);
        gender = findViewById(R.id.exGender);
        specialization = findViewById(R.id.exSpecialization);
        bio = findViewById(R.id.exBio);
        patientStuff = findViewById(R.id.exPatient);

        Intent intent = getIntent();
        Doctor.Profile doctorProfile = (Doctor.Profile) intent.getSerializableExtra(EXTRA_DOCTOR_PROFILE);
        Patient.Profile patientProfile = (Patient.Profile) intent.getSerializableExtra(EXTRA_PATIENT_PROFILE);

        String spec = intent.getStringExtra(EXTRA_SPECFILTER);
        name.setText(doctorProfile.getFirstName() + " " + doctorProfile.getLastName());
        gender.setText(doctorProfile.getGender());
        specialization.setText(spec); // alternatively, use doctorProfile.getSpecialization()
        bio.setText(doctorProfile.getBio());
        patientStuff.setText(patientProfile.getFirstName() + " " + patientProfile.getLastName() + ", " + patientProfile.getMedicalCondition());


        /*
         * This is currently a empty activity that appears when selecting
         * doctor from the filtering/"find a specialist" page.
         * see line 47 in RecyclerViewAdapter.
         */

        // Some stuff I tried but didn't work
        //Intent intent = getIntent();
        //Doctor doc = (Doctor) intent.getParcelableExtra(RecyclerViewAdapter.EXTRA_MESSAGE);

        //TextView textView = findViewById(R.id.textView);
        //textView.setText(doc.getFirstName() + " " + doc.getLastName());
    }

}