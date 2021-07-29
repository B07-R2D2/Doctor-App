package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


/**
 * this version is OLD, and saved for possible future use. We now *try* to use AvailabilityRecyclerView instead
 */
public class AvailabilityActivity extends AppCompatActivity {

//    ArrayList<String> listOfDates = new ArrayList<String>(new String[]{"2 to 3 pm", "3 to 4 pm"});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
    }
}