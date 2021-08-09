package com.r2d2.doctorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorHomePageActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;
    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), username);

        profileButton = (Button) findViewById(R.id.doctorProfileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DoctorHomePageActivity", "clicked profile button");
                Intent intent = new Intent(v.getContext(), DoctorProfileActivity.class);
                intent.putExtra(DoctorProfileActivity.EXTRA_DOCTOR_PROFILE, doctor.getProfile());
                v.getContext().startActivity(intent);
            }
        });
    }

}
