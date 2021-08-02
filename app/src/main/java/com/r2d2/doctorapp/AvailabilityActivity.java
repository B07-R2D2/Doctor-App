package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

// Note:  AvailablilitySchedule will need to implement Serializable (to be passed into this Activity)
//        DateTimeInterval will need to implement Comparable<DateTimeInterval> (hashset -> ArrayList -> sort and display)
public class AvailabilityActivity extends AppCompatActivity {
    private AvailabilitySchedule schedule;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        recyclerView = findViewById(R.id.availability_recycler_view);   // set the recycler view

        // after the patient click the doctor, we will receive an intent, which includes a schedule
        Intent intent = getIntent();
        schedule = (AvailabilitySchedule)intent.getSerializableExtra("AvailabilitySchedule");                         // initialize schedule
        
        setAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setAdapter() {
        // the adapter takes in a schedule and displays the contents
        RecyclerAdapter adapter = new RecyclerAdapter(schedule);

        // sets the layout, default animator, and adapter of recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


}