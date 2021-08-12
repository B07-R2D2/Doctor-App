package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

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
import java.util.function.UnaryOperator;

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

        Intent intent = getIntent();
        username = intent.getStringExtra(EXTRA_USERNAME);
        doctorCalendar = new DoctorCalendar();
        doctorCalendar.fetchAppointments(username, this::setAdaptor);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setAdaptor() {
        List<Appointment> apptlists= doctorCalendar.getAppointments();
        filterTodaySchedule(apptlists);
        recyclerAdapterDoctorHome adaptor = new recyclerAdapterDoctorHome(apptlists, doctorCalendar.getPatientProfiles(), "DoctorHome");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void filterTodaySchedule(List<Appointment> apptlists){
        Instant instant = Instant.now();
        long currTime = instant.getEpochSecond();

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("MM/dd")
                .withZone(ZoneId.of("America/Toronto"))
                .withLocale(Locale.getDefault());

        String sTime = formatter.format(Instant.ofEpochSecond(currTime));
        List<Appointment> temp = new ArrayList<>();

        for(Appointment a : apptlists){
            long appttime = a.getTimeStamp();

            String sApptTime = formatter.format(Instant.ofEpochSecond(appttime));
            if(appttime > currTime && sTime.equals(sApptTime)){
                temp.add(a);
            }
        }
        apptlists.clear();
        apptlists.addAll(temp);
    }

    public void viewFullWeekSchedule(View view){
        Intent intent2 = new Intent(this,DoctorCalendarActivity.class);
        intent2.putExtra(setUSERNAME, username);
        startActivity(intent2);
    }
    public void toProfileButton(View view){
        Intent intent3 = new Intent(this,DoctorProfileActivity.class);
        intent3.putExtra(setUSERNAME, new Doctor.Profile());
        startActivity(intent3);
    }
}
