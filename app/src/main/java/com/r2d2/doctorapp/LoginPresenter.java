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
    public void checkLogin(String username, String password)
    {
        int found = model.checkLogin(username, password);
        if(found == 0)
            view.displayErrorMessage(view.getString(R.string.login_user_not_found));
        else {
            Intent intent = new Intent(view, (found == 1) ? PatientHomeActivity.class : DoctorHomePageActivity.class);
            intent.putExtra(view.givenUsername, username);
            view.startActivity(intent);
        }
    }
}
