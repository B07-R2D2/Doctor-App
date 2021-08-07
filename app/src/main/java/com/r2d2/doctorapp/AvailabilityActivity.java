package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        Doctor doctor = (Doctor)intent.getSerializableExtra("Doctor");

        String name = doctor.getUsername();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Doctors").child(name);
        this.schedule = new AvailabilitySchedule(ref, GregorianCalendar.getInstance());

        Log.i("receiveDoc", doctor.toString() + " received");
        Log.i("receiveDoc", "there are " + schedule.timeSlots().size() + " timeslots");
        for (DateTimeInterval time : schedule.timeSlots())
            Log.i("receiveDoc", "including: " + time.toString());
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

    /**
     * this method is ONLY for TESTING AvailabilityRecyclerView
     */
    private void setTimeSlotInfo() {
        schedule.addTimeSlot(new DateTimeInterval(new Date(2021, 8, 6, 10, 30), new Date(2021, 8, 6, 11, 30)));
        schedule.addTimeSlot(new DateTimeInterval(new Date(2021, 8, 6, 12, 30), new Date(2021, 8, 6, 13, 30)));
        schedule.addTimeSlot(new DateTimeInterval(new Date(2021, 8, 6, 15, 30), new Date(2021, 8, 6, 6, 30)));

    }


}