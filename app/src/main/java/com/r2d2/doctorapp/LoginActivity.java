package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    public static final String Username = "com.example.DoctorApp.MESSAGE";
    public static final String Password = "com.example.DoctorApp.MESSAGE";
    private final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    @Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot child : snapshot.getChildren()) {
            User user = child.getValue(User.class);
            Log.i("username",user.getFirstName());
            Log.i("password",user.getFirstName());
        }
    }
    @Override
    public void onCancelled(DatabaseError error) {
        Log.w("AvailabilitySchedule", error.toException());
    }
    public void sendInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, PatientSignupActivity.class);
        EditText send = (EditText) findViewById(R.id.EnterUsername);
        EditText send2 = (EditText) findViewById(R.id.EnterPassword);
        String usernameMessage = send.getText().toString();
        String passwordMessage = send2.getText().toString();
        intent.putExtra(Username, usernameMessage);
        intent.putExtra(Password, passwordMessage);
        //reference database on whether patient exists
        if(Username == ref.child())
        {
            if(Password = ref.Username.);
        }
        startActivity(intent);
    }
    public void sendPatientSignup(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, PatientSignupActivity.class);
        startActivity(intent);
    }
    public void sendDoctorSignup(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, PatientSignupActivity.class);
        startActivity(intent);
    }

}