package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    public static final String Username = "com.example.DoctorApp.MESSAGE";
    public static final String Password = "com.example.DoctorApp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sendInfo(View view) {
        // Do something in response to button
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        EditText send = (EditText) findViewById(R.id.EnterUsername);
        EditText send2 = (EditText) findViewById(R.id.EnterPassword);
        String usernameMessage = send.getText().toString();
        String passwordMessage = send2.getText().toString();
        intent.putExtra(Username, usernameMessage);
        intent.putExtra(Password, passwordMessage);
        startActivity(intent);
    }

}