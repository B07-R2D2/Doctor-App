package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    public static final String Username = "com.example.DoctorApp.MESSAGE";
    public static final String Password = "com.example.DoctorApp.MESSAGE";
    //private final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    /*
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            User user = child.getValue(User.class);
            Log.i("username",user.getUsername());
            Log.i("password",user.getPassword());
        }
    }

    public void onCancelled(DatabaseError error) {
        Log.w("AvailabilitySchedule", error.toException());
    }
    */
    public void sendHomepage(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
        EditText send = (EditText) findViewById(R.id.EnterUsername);
        EditText send2 = (EditText) findViewById(R.id.EnterPassword);
        String usernameMessage = send.getText().toString();
        String passwordMessage = send2.getText().toString();
        intent.putExtra(Username, usernameMessage);
        intent.putExtra(Password, passwordMessage);
        //reference database to check whether patient exists

        startActivity(intent);
    }
    public void sendPatientSignup(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, PatientSignupActivity.class);
        startActivity(intent);
    }
    public void sendDoctorSignup(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, DoctorSignupActivity.class);
        startActivity(intent);
    }

}