package com.r2d2.doctorapp;

import android.content.Intent;

public class LoginPresenter {
    private LoginModel model;
    private LoginActivity view;

    public LoginPresenter(LoginModel Model, LoginActivity View)
    {
        this.model = Model;
        this.view = View;
    }
    public void checkLogin(String Username, String Password)
    {
        Intent intent = new Intent(view, PatientHomeActivity.class);
        Intent intent2 = new Intent(view,DoctorHomePageActivity.class);
        intent.putExtra(view.givenUsername, Username);
        intent2.putExtra(view.givenUsername, Username);
        int found = model.checkLogin(Username,Password);
        if(found == 0)
            view.displayMessage();
        else if(found == 1)
            view.startActivity(intent);
        else
            view.startActivity(intent2);
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
