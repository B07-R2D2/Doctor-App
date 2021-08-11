package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DoctorCalendarActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = DoctorHomePageActivity.EXTRA_USERNAME;

    private static ArrayList<Appointment> appointments;
    private static ArrayList<Patient.Profile> patientProfiles;
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

            fetchedCount = 0;
            totalCount = appointments.size();

            for (Appointment appointment : appointments) {
                appointments.add(appointment);

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