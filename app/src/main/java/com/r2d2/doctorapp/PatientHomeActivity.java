package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PatientHomeActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;

    private PatientHome presenter;

    private TextView upcomingAppointmentsLabel;
    private RecyclerView upcomingAppointmentsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Intent intent = getIntent();
        Patient patient = new Patient(FirebaseDatabase.getInstance(), intent.getStringExtra(EXTRA_USERNAME));
        presenter = new PatientHome(patient, this);

        upcomingAppointmentsLabel = (TextView) findViewById(R.id.patient_home_label_upcoming_appointments);
        upcomingAppointmentsRecycler = (RecyclerView) findViewById(R.id.upcoming_appointments_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        upcomingAppointmentsRecycler.setLayoutManager(layoutManager);
        upcomingAppointmentsRecycler.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        upcomingAppointmentsRecycler.setItemAnimator(new DefaultItemAnimator());

        setUpcomingAppointmentInfo(new ArrayList<>());
        patient.addObserver(this::updateUpcomingAppointments);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        updateUpcomingAppointments();
    }

    private void updateUpcomingAppointments() {
        presenter.fetchUpcomingAppointmentInfo(this::setUpcomingAppointmentInfo);
    }

    private void setUpcomingAppointmentInfo(List<String> info) {
        upcomingAppointmentsLabel.setText(info.isEmpty() ? R.string.patient_home_label_no_upcoming_appointments : R.string.patient_home_label_upcoming_appointments);
        upcomingAppointmentsRecycler.setAdapter(new PlainTextRecyclerAdapter(info));
    }

    public void findSpecialistButtonClicked(View view) {
        presenter.goToFindSpecialist();
    }

    public void viewProfileButtonClicked(View view) {
        presenter.goToPatientProfile();
    }

}
