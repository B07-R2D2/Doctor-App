package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorCalendarActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = DoctorHomePageActivity.EXTRA_USERNAME;

    private RecyclerView recyclerView;

    private DoctorCalendar doctorCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_calendar);

        recyclerView = findViewById(R.id.recyclerView);

        Intent da1 = getIntent();
        String username = da1.getStringExtra(DoctorHomePageActivity.setUSERNAME);
        doctorCalendar = new DoctorCalendar();
        doctorCalendar.fetchAppointments(username, this::setAdaptor);
    }

    public void setAdaptor() {
        recyclerAdapterDoctorCalender adaptor = new recyclerAdapterDoctorCalender(doctorCalendar.getAppointments(), doctorCalendar.getPatientProfiles());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }
}