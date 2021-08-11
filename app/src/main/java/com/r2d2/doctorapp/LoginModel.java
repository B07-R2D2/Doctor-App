package com.r2d2.doctorapp;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LoginModel {
    private final Map<String, User> patients = new HashMap<>(), doctors = new HashMap<>();

    public LoginModel(FirebaseDatabase db) {
        listenForUsers(db.getReference("Patients"), patients, username -> new Patient(db, username));
        listenForUsers(db.getReference("Doctors"), doctors, username -> new Doctor(db, username));
    }

    private void listenForUsers(DatabaseReference ref, Map<String,User> users, Function<String, User> createUser) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String username = child.getKey();
                    users.put(username, createUser.apply(username));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /**
     * Check if a user exists for the given username and password.
     * @param username username to look for
     * @param password password to look for
     * @return type of user: if no user found, 0; if patient found, 1; if doctor found, 2
     */
    public int checkLogin(String username, String password)
    {
        User user = patients.get(username);
        if (user == null) user = doctors.get(username);
        if (user != null && user.getProfile().getPassword().equals(password)) {
            return (user instanceof Patient) ? 1 : 2;
        }
        return 0;
    }
}
