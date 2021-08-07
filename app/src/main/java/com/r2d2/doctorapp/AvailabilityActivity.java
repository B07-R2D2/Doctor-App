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
    private Doctor doctor;              // the doctor's we're viewing
    private Patient patient;            // the patient who logged in
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w("Patient", "started availability activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        recyclerView = findViewById(R.id.availability_recycler_view);   // set the recycler view

        // after the patient click the doctor, we will receive an intent, which includes a schedule
        Intent intent = getIntent();
        this.doctor = (Doctor)intent.getSerializableExtra("Doctor");
        Log.i("Doctor", "received doctor in availability activity: " + this.doctor.getUsername());

        this.patient = (Patient)intent.getSerializableExtra(DoctorHistoryActivity.EXTRA_PATIENT);
        Log.i("Patient", "received patient in Availabiltiy activity: " + this.patient.getUsername());

        setAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setAdapter() {
        // the adapter takes in a schedule and displays the contents
        Log.i("Patient", "sending patient to recyclerAdapter");
        RecyclerAdapter adapter = new RecyclerAdapter(doctor, patient);

        // sets the layout, default animator, and adapter of recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}