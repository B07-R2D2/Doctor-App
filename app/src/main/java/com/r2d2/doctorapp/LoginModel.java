package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginModel {
    private final List<User> users = new ArrayList<>();
    //initialize the databases

    public LoginModel(DatabaseReference p, DatabaseReference d)
    {
        this.pat = p;
        this.doc = d;
        ValueEventListener patientListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    users.add(new Patient(db, child.getKey()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("LoginActivity", error.toException());
            }
        });
        db.getReference("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    users.add(new Doctor(db, child.getKey()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("LoginActivity", error.toException());
            }
        });
    }

    //check login and return corresponding int to signify Patient,Doctor,Or User Does not Exist
    public int checkLogin(String Username, String Password)
    {
        int userType = 0;
        for(User user : users)
        {
            if(user.getProfile().getUsername().equals(username) && user.getProfile().getPassword().equals(password))
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
