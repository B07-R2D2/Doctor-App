package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String givenUsername = "com.example.DoctorApp.USERNAMEMESSAGE";
    private static final DatabaseReference pat = FirebaseDatabase.getInstance().getReference("Patients");
    private static final DatabaseReference doc = FirebaseDatabase.getInstance().getReference("Doctors");
    private static final List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText passwordEdit = (EditText) findViewById(R.id.EnterPassword);
        passwordEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    checkLogin(null);
                    return true;
                }
                return false;
            }
        });
    }
    //static adds ValueEvent Listener Automatically
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
    public void checkLogin(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PatientHomeActivity.class);
        Intent intent2 = new Intent(this,DoctorHomePageActivity.class);
        EditText send = (EditText) findViewById(R.id.EnterUsername);
        EditText send2 = (EditText) findViewById(R.id.EnterPassword);
        String usernameMessage = send.getText().toString();
        String passwordMessage = send2.getText().toString();
        intent.putExtra(givenUsername, usernameMessage);
        intent2.putExtra(givenUsername, usernameMessage);
        //loop through hashmap to check if user is patient or doctor and go into the corresponding homepage
        for(User user : users)
        {
            if(user.getProfile().getUsername().equals(usernameMessage) && user.getProfile().getPassword().equals(passwordMessage))
            {
                startActivity((user instanceof Patient) ? intent : intent2);
            }
        }
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