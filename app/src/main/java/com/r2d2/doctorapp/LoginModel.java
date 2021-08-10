package com.r2d2.doctorapp;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginModel {
    private static final DatabaseReference pat = FirebaseDatabase.getInstance().getReference("Patients");
    private static final DatabaseReference doc = FirebaseDatabase.getInstance().getReference("Doctors");
    private static final List<User> users = new ArrayList<>();
    public LoginModel() {

    }
    static
    {
        ValueEventListener patientListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    users.add(new Patient(pat.getDatabase(), child.getKey()));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("LoginActivity", error.toException());
            }
        };
        ValueEventListener doctorListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    users.add(new Doctor(doc.getDatabase(), child.getKey()));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("LoginActivity", error.toException());
            }
        };
        pat.addValueEventListener(patientListener);
        doc.addValueEventListener(doctorListener);
    }
    public int checkLogin(String Username, String Password)
    {
        int userType = 0;
        for(User user : users)
        {
            if(user.getProfile().getUsername().equals(Username) && user.getProfile().getPassword().equals(Password))
            {
                if((user instanceof Patient))
                    userType = 1;
                else
                    userType = 2;
            }
        }
        return userType;
    }
}
