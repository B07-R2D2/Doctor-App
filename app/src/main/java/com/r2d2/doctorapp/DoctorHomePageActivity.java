package com.r2d2.doctorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorHomePageActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;
    private DoctorHome home;
    private static ArrayList<Patient.Profile> patientlists;
    private RecyclerView recyclerView;
//    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("doctors").child(EXTRA_USERNAME).child("appointments");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        recyclerView = findViewById(R.id.rvdhome);
        patientlists = new ArrayList<Patient.Profile>();

        Intent intent = getIntent();
        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), intent.getStringExtra(EXTRA_USERNAME));
        home = new DoctorHome(doctor, this);

        DatabaseReference ref = doctor.getRef().child("appointments");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    Patient.Profile doc = child.getValue(Patient.Profile.class);
                    if(child.child("patientName").getKey() != ""){
                        patientlists.add(new Patient.Profile());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost: onCancelled", error.toException());
            }
        };
        setAdaptor();
    }

    public void setAdaptor(){
        recyclerAdapterDoctorHome adaptor = new recyclerAdapterDoctorHome(patientlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    public void viewPatientInfo(View view){
        home.findPatientInfo();

        Intent intent = new Intent(this, PatientProfileActivity.class);
        startActivity(intent);
    }
}