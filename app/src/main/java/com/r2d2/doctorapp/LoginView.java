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

public class LoginView {
    private final LoginActivity view;
    private static final DatabaseReference pat = FirebaseDatabase.getInstance().getReference("Patients");
    private static final DatabaseReference doc = FirebaseDatabase.getInstance().getReference("Doctors");
    private static final List<User> users = new ArrayList<>();
    public LoginView(LoginActivity view) {
        this.view = view;
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
    public void checkLogin(String Username, String Password)
    {
        Intent intent = new Intent(view, PatientHomeActivity.class);
        Intent intent2 = new Intent(view,DoctorHomePageActivity.class);
        intent.putExtra(view.givenUsername, Username);
        intent2.putExtra(view.givenUsername, Username);
        Log.i("LoginView", "inside checkLogin");
        Log.i("LoginView", "inside checkLogin2" + users.toString());
        for(User user : users)
        {
            if(user.getProfile().getUsername().equals(Username) && user.getProfile().getPassword().equals(Password))
            {
                view.startActivity((user instanceof Patient) ? intent : intent2);
            }
        }
    }
    public void sendPatientSignup() {
        // Do something in response to button
        Intent intent = new Intent(view, PatientSignupActivity.class);
        view.startActivity(intent);
    }
    public void sendDoctorSignup() {
        // Do something in response to button
        Intent intent = new Intent(view, DoctorSignupActivity.class);
        view.startActivity(intent);
    }

}
