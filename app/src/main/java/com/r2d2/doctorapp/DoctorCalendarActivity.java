package com.r2d2.doctorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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

import java.util.ArrayList;

public class DoctorCalendarActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = DoctorHomePageActivity.EXTRA_USERNAME;

    private static ArrayList<Appointment> apptlists;
    private static ArrayList<Patient.Profile> ppList;
    private RecyclerView recyclerView;
    private String username;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_calendar);

        Intent da1 = getIntent();
        this.username = da1.getStringExtra(DoctorHomePageActivity.setUSERNAME);
        this.names = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        apptlists = new ArrayList<>();
        ppList = new ArrayList<>();
//        Intent intent = getIntent();
//        this.username = intent.getStringExtra(EXTRA_USERNAME);
        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), username);
//
        DatabaseReference ref = doctor.getRef().child("appointments");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()) {
                    Appointment appt = child.getValue(Appointment.class);
                    if(!appt.getPatientName().equals("")){
                        apptlists.add(appt);
                        names.add(appt.getPatientName());
                        DatabaseReference ppref = FirebaseDatabase.getInstance().getReference("Patients").child(appt.getPatientName());
                        ppref.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Patient.Profile currentProfile = snapshot.getValue(Patient.Profile.class);
                                ppList.add(currentProfile);
                                setAdaptor();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.w("warning", "failed to get patientprofile, DoctorCalendarPage", error.toException());
                            }
                        });
                    }else{
                        names.add("");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("warning", "loadPost: onCancelled", error.toException());
            }
        });
    }
    public void setAdaptor(){

        recyclerAdapterDoctorCalender adaptor = new recyclerAdapterDoctorCalender(apptlists, ppList, names);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptor);
    }
}