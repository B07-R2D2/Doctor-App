package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterListActivity extends AppCompatActivity {
    public static final String EXTRA_DOCTOR_PROFILE = "com.r2d2.DoctorApp.RegisterListActivity.extra_doctor_profile";
    private ArrayList<Patient.Profile> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_list);

        Intent intent = getIntent();
        Doctor.Profile doctorProfile = (Doctor.Profile) intent.getSerializableExtra(EXTRA_DOCTOR_PROFILE);

        DatabaseReference patRef = FirebaseDatabase.getInstance().getReference("Patients");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Doctors").child(doctorProfile.getUsername()).child("past patients");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Iterates over the doctor's past patients
                for (DataSnapshot child : snapshot.getChildren()){
                    // Keys are the patient usernames
                    String patientUsername = child.getKey();
                    // probably not the best way to do this, will figure out later
                    // also not even sure if it works
                    Patient patient = new Patient(FirebaseDatabase.getInstance(), patientUsername);
                    patient.addOneTimeObserver(()-> {
                        patientList.add(patient.getProfile());
                        initRecyclerView();
                    });
                }
                //initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.registerListRecycler);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2(this, patientList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}