package com.r2d2.doctorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class PatientHomeActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;

    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        FirebaseDatabase.getInstance().getReference("Patients").child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patient = snapshot.getValue(Patient.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("PatientHomeActivity", error.toException());
            }
        });
    }

    public void viewHistoryButtonClicked(View view) {
        Intent intent = new Intent(this, DoctorHistoryActivity.class);
        intent.putExtra(DoctorHistoryActivity.EXTRA_PATIENT, patient);
        startActivity(intent);
    }

    public void viewProfileButtonClicked(View view) {
        Intent intent = new Intent(this, PatientProfileActivity.class);
        intent.putExtra(PatientProfileActivity.EXTRA_PATIENT, patient);
        startActivity(intent);
    }

}
