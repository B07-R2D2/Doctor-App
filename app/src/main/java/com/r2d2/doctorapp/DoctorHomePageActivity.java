package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorHomePageActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;
    
//    private static ArrayList<Patient.Profile> apptlists;

    // find list of appointments, and then get corresponding patient profiles
    private static ArrayList<Appointment> apptlists;
    private static ArrayList<Patient.Profile> ppList;
    private RecyclerView recyclerView;

    private ArrayList<String> testing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        recyclerView = findViewById(R.id.rvdhome);
        apptlists = new ArrayList<>();
        ppList = new ArrayList<>();

        Intent intent = getIntent();
        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), intent.getStringExtra(EXTRA_USERNAME));
        // home = new DoctorHome(doctor, this);

        DatabaseReference ref = doctor.getRef().child("appointments");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    Appointment appt = child.getValue(Appointment.class);
                    if(!appt.getPatientName().equals("")){
                        apptlists.add(appt);
                        Log.w("info", "emptyname " + appt.getPatientName());
                    }
                }
                setAdaptor();
                Log.w("info", "apptlists size in onDataChange" + apptlists.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost: onCancelled", error.toException());
            }
        });


        Log.w("info", "apptlists size after onDataChange befor for" + apptlists.size());
        // not sure if we use addValueEventListener or addListenerForSingleValueEvent
        // appointment list
        // profile list
        // loop through appointment list, for each name, find corresponding profile, and add to pplist.
        for (Appointment a : apptlists) {
            String name = a.getPatientName();
            DatabaseReference ppref = FirebaseDatabase.getInstance().getReference("patients").child(name);
            ppref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Patient.Profile currentProfile = snapshot.getValue(Patient.Profile.class);
                    ppList.add(currentProfile);

                    Log.w("info", "apptlists size in onDataChange patient.profile" + apptlists.size());
                    Log.w("info", "ppList size in onDataChange patient.profile" + ppList.size());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("warning", "failed to get patientprofile, DoctorHomePage", error.toException());
                }
            });
        }

        Log.w("info", "apptlists size before setAdaptor " + apptlists.size());
//        setAdaptor();
    }

    public void setAdaptor(){

        Log.w("info", "apptlists size in setAdaptor " + apptlists.size());
        recyclerAdapterDoctorHome adaptor = new recyclerAdapterDoctorHome(apptlists, ppList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }

    public void viewPatientInfo(View view){
//        Intent intent = new Intent(this, PatientProfileActivity.class);
//
////        intent.putExtra("PatientProfile", patient);
//        startActivity(intent);
    }
}
