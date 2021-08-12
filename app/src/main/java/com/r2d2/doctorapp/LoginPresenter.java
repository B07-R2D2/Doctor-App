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

    /**
     * Check if a user exists for the given username and password.
     * If such a user exists, log in as that user. Otherwise, display an error message.
     * @param username username to look for
     * @param password password to look for
     */
    public void checkLogin(String username, String password)
    {
        int found = model.checkLogin(username, password);
        if(found == 0)
            view.displayErrorMessage(view.getString(R.string.login_user_not_found));
        else {
            Intent intent = new Intent(view, (found == 1) ? PatientHomeActivity.class : DoctorHomePageActivity.class);
            intent.putExtra(view.givenUsername, username);
            view.startActivity(intent);
            view.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }
}
