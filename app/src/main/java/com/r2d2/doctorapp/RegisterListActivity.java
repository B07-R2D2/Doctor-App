package com.r2d2.doctorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RegisterListActivity extends AppCompatActivity {
    public static final String EXTRA_DOCTOR_PROFILE = "com.r2d2.DoctorApp.RegisterListActivity.extra_doctor_profile";
    private ArrayList<Patient.Profile> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_list);

        Intent intent = getIntent();
        Doctor.Profile doctorProfile = (Doctor.Profile) intent.getSerializableExtra(EXTRA_DOCTOR_PROFILE);

        DatabaseReference ref0 = FirebaseDatabase.getInstance().getReference("Doctors");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Patients");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()){
                    Patient.Profile pat = child.getValue(Patient.Profile.class);
                    patientList.add(pat);
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("RLActivity onCancelled", error.toException());
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