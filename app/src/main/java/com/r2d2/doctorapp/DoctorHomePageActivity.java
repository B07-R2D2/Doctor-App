package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
    }

    public void goToCalender(View view)
    {
        Intent intent = new Intent(this,DoctorCalendarActivity.class);
        startActivity(intent);
    }
    public void goToHome(View view)
    {
        Intent intent = new Intent(this,DoctorHomePageActivity.class);
        startActivity(intent);
    }
    public void goToProfile(View view)
    {
        Intent intent = new Intent(this,DoctorProfileActivity.class);
        startActivity(intent);
    }
}