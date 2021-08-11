package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DoctorCalendarActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = DoctorHomePageActivity.EXTRA_USERNAME;

    private RecyclerView recyclerView;

    private DoctorCalendar doctorCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_calendar);

        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        String username = intent.getStringExtra(DoctorHomePageActivity.setUSERNAME);
        doctorCalendar = new DoctorCalendar();
        doctorCalendar.fetchAppointments(username, this::setAdaptor);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setAdaptor() {
        List<Appointment> apptlists= doctorCalendar.getAppointments();
        filterTodayWeek(apptlists);
        recyclerAdapterDoctorHome adaptor = new recyclerAdapterDoctorHome(apptlists, doctorCalendar.getPatientProfiles(), "DoctorCalendar");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void filterTodayWeek(List<Appointment> apptlists){
        Instant instant = Instant.now();
        long currTime = instant.getEpochSecond();

        List<Appointment> temp = new ArrayList<>();

        for(Appointment a : apptlists){
            long appttime = a.getTimeStamp();

            if(appttime > currTime){
                temp.add(a);
            }
        }
        apptlists.clear();
        apptlists.addAll(temp);
    }
}