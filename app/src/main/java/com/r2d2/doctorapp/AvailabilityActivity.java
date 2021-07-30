package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AvailabilityActivity extends AppCompatActivity {

    private ArrayList<DateTimeInterval> timeSlotList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_availability);
        recyclerView = findViewById(R.id.availability_recycler_view);
        timeSlotList = new ArrayList<>();

        // this is just for testing
        setTimeSlotInfo();
        setAdapter();
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(timeSlotList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // set adapter
        recyclerView.setAdapter(adapter);
    }

    /**
     * this method is ONLY for TESTING AvailabilityRecyclerView
     */
    private void setTimeSlotInfo() {
        timeSlotList.add(new DateTimeInterval("Monday 5pm"));
        timeSlotList.add(new DateTimeInterval("Monday 6pm"));
        timeSlotList.add(new DateTimeInterval("Monday 7pm"));
        timeSlotList.add(new DateTimeInterval("Monday 8pm"));

    }


}