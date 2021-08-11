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

    public LoginModel(FirebaseDatabase db)
    {
        db.getReference("Patients").addValueEventListener(new ValueEventListener() {
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

    /**
     * Checks if a user exists for the given username and password.
     * @param username username to look for
     * @param password password to look for
     * @return type of user: if no user found, 0; if patient found, 1; if doctor found, 2
     */
    public int checkLogin(String username, String password)
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
