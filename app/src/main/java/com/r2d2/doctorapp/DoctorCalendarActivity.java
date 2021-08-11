package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctorCalendarActivity extends AppCompatActivity {

    private static final String LOG_TAG = "DoctorCalendarActivity";

    public static final String EXTRA_USERNAME = DoctorHomePageActivity.EXTRA_USERNAME;

    private static List<Appointment> appointments;
    private static List<Patient.Profile> patientProfiles;
    private RecyclerView recyclerView;
    private String username;

    private int fetchedCount, totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_calendar);

        Intent da1 = getIntent();
        this.username = da1.getStringExtra(DoctorHomePageActivity.setUSERNAME);

        recyclerView = findViewById(R.id.recyclerView);
        appointments = new ArrayList<>();
        patientProfiles = new ArrayList<>();

        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), username);
        doctor.addOneTimeObserver(() -> {
            // Partly copied from AppointmentHistory
            appointments = doctor.getProfile().getAppointments();

            fetchedCount = 0;
            totalCount = appointments.size();
            Log.d(LOG_TAG, "Doctor has " + totalCount + " appointments");

            for (Appointment appointment : appointments) {
                String patientName = appointment.getPatientName();
                if (patientName.isEmpty()) {
                    patientProfiles.add(null);
                    checkIfFetchedAll();
                } else {
                    Patient patient = new Patient(doctor.getRef().getDatabase(), appointment.getPatientName());
                    patient.addOneTimeObserver(() -> {
                        patientProfiles.add(patient.getProfile());
                        checkIfFetchedAll();
                    });
                }
            }
        });
    }

    private void checkIfFetchedAll() {
        fetchedCount++;
        Log.d(LOG_TAG, "Fetched " + fetchedCount + " of " + totalCount);
        if (fetchedCount == totalCount) {
            // We've fetched everything.
            setAdaptor();
        }
    }

    public void setAdaptor(){
        recyclerAdapterDoctorCalender adaptor = new recyclerAdapterDoctorCalender(appointments, patientProfiles);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }
}