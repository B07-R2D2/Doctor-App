package com.r2d2.doctorapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String givenUsername = "com.example.DoctorApp.USERNAMEMESSAGE";
    private LoginView presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //This is the app entry point.
        //PoorMansBackend.getInstance().start();

        presenter = new LoginView(this);
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
    public void checkLogin(View view) {
        // Do something in response to button
        EditText send = (EditText) findViewById(R.id.EnterUsername);
        EditText send2 = (EditText) findViewById(R.id.EnterPassword);
        String usernameMessage = send.getText().toString();
        String passwordMessage = send2.getText().toString();
        presenter.checkLogin(usernameMessage,passwordMessage,send2);
        //loop through hashmap to check if user is patient or doctor and go into the corresponding homepage
    }
    public void sendPatientSignup(View view) {
        // Do something in response to button
        presenter.sendPatientSignup();
    }
    public void sendDoctorSignup(View view) {
        // Do something in response to button
        presenter.sendDoctorSignup();
    }

    @Override
    protected void onDestroy() {
        // This is the app exit point.
        //PoorMansBackend.getInstance().stop();

        super.onDestroy();
    }
}
