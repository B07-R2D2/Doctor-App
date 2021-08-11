package com.r2d2.doctorapp;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.database.FirebaseDatabase;

public class PatientSignupPresenter {
    private final Activity view;

    public PatientSignupPresenter(Activity view) {
        this.view = view;
    }

    public void signUp(String username, String password, String firstname, String lastname, int sin, String gender, String medicalCondition, Long dateOfBirth)
    {
        // makes a new patient that is added to database as the constructor in patient does that automatically
        new Patient(
            FirebaseDatabase.getInstance(),
            new Patient.Profile(firstname, lastname, username, password, gender, sin, medicalCondition, dateOfBirth)
        );

        //go back to login page to sign in with the newly added user
        Intent backToLogin = new Intent(view, LoginActivity.class);
        view.startActivity(backToLogin);
    }
    public void back()
    {
        Intent intent = new Intent(view,LoginActivity.class);
        view.startActivity(intent);
    }
}
