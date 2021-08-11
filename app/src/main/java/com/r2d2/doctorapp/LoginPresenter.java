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
}
