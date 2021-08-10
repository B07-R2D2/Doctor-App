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
        int found = model.checkLogin(Username,Password);
        if(found == 0)
            view.displayMessage();
        else if(found == 1)
            view.sendPatientSignup(Username);
        else
            view.sendDoctorSignup(Username);
    }
}
