package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorHomePageActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;
    public static final String setUSERNAME = "com.example.DoctorApp.SETUSERMESSAGE";

    private RecyclerView recyclerView;

    private String username;
    private DoctorCalendar doctorCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        recyclerView = findViewById(R.id.rvdhome);

        Intent da1 = getIntent();
        username = da1.getStringExtra(EXTRA_USERNAME);
        doctorCalendar = new DoctorCalendar();
        doctorCalendar.fetchAppointments(username, this::setAdaptor);
    }

    public void setAdaptor() {
        recyclerAdapterDoctorHome adaptor = new recyclerAdapterDoctorHome(doctorCalendar.getAppointments(), doctorCalendar.getPatientProfiles());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    public void viewFullWeekSchedule(View view){
        Intent intent2 = new Intent(this,DoctorCalendarActivity.class);
        intent2.putExtra(setUSERNAME, username);
        startActivity(intent2);
    }
}
