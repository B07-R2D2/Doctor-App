package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    public static final String givenUsername = "com.example.DoctorApp.USERNAMEMESSAGE";

    private LoginPresenter presenter;

    public void displayErrorMessage(String message)
    {
        EditText send = (EditText) findViewById(R.id.EnterPassword);
        send.setError(message);
        send.requestFocus();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // This is the app entry point, so start background tasks here.
        PoorMansBackend.getInstance().start();

        presenter = new LoginPresenter(new LoginModel(FirebaseDatabase.getInstance()),this);

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

    public void checkLogin(View view) {
        // Do something in response to button
        EditText usernameField = (EditText) findViewById(R.id.EnterUsername);
        EditText passwordField = (EditText) findViewById(R.id.EnterPassword);
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        presenter.checkLogin(username, password);
    }
    public void sendPatientSignup(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, PatientSignupActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
    public void sendDoctorSignup(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DoctorSignupActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

}
