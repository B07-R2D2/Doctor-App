package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    public static final String givenUsername = "com.example.DoctorApp.USERNAMEMESSAGE";
    private LoginPresenter presenter;

    public void displayMessage()
    {
        EditText send = (EditText) findViewById(R.id.EnterPassword);
        send.setError("Username or Password is incorrect or account does not exist");
        send.requestFocus();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //This is the app entry point.
        //PoorMansBackend.getInstance().start();
        LoginModel m = new LoginModel();
        LoginPresenter presenter = new LoginPresenter(m,this);
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
        presenter.checkLogin(usernameMessage,passwordMessage);
        //loop through hashmap to check if user is patient or doctor and go into the corresponding homepage
    }
    public void sendPatientSignup(String Username) {
        // Do something in response to button
        Intent intent = new Intent(this, PatientSignupActivity.class);
        intent.putExtra(givenUsername,Username);
        this.startActivity(intent);
    }
    public void sendDoctorSignup(String Username) {
        // Do something in response to button
        Intent intent = new Intent(this, DoctorSignupActivity.class);
        intent.putExtra(givenUsername, Username);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        // This is the app exit point.
        PoorMansBackend.getInstance().stop();

        super.onDestroy();
    }
}
