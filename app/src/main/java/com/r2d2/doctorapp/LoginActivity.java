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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    public static final String Username = "com.example.DoctorApp.MESSAGE";
    public static final String Password = "com.example.DoctorApp.MESSAGE";
    private final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private HashMap<String,String> userMap=new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addUserEventListener(ref);
    }

    private void addUserEventListener(DatabaseReference ref) {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    userMap.put(user.getUsername(),user.getPassword());
                    Log.i("username", user.getUsername());
                    Log.i("password", user.getPassword());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("LoginActvity", error.toException());
            }
        };
        ref.addValueEventListener(userListener);
    }


    public void checkLogin(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
        EditText send = (EditText) findViewById(R.id.EnterUsername);
        EditText send2 = (EditText) findViewById(R.id.EnterPassword);
        String usernameMessage = send.getText().toString();
        String passwordMessage = send2.getText().toString();
        intent.putExtra(Username, usernameMessage);
        intent.putExtra(Password, passwordMessage);
        //reference hashmap to check whether user exists
        //onCreate adds valueeventlistener, which will update the database and add info to the hashmap
        //which this can read from and check if the username and password match correctly
        for(HashMap.Entry<String, String> entry: userMap.entrySet())
        {
            if(entry.getKey() == Username && entry.getValue() == Password)
            {
                startActivity(intent);
            }
        }
        //startActivity(intent);
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