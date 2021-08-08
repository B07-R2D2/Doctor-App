package com.r2d2.doctorapp;

import android.content.Intent;
import android.view.View;

public class PatientSignupView {
    private final PatientSignupActivity view;

    public PatientSignupView(PatientSignupActivity view) {
        this.view = view;
    }

    public void sendInfo(String setU, String setP, String setF, String setL)
    {
        Intent intent = new Intent(view, PatientSignup2Activity.class);
        intent.putExtra(view.setUSERNAME, setU);
        intent.putExtra(view.setPASSWORD, setP);
        intent.putExtra(view.setFIRSTNAME, setF);
        intent.putExtra(view.setLASTNAME, setL);
        view.startActivity(intent);
    }
    public void back()
    {
        Intent intent = new Intent(view,LoginActivity.class);
        view.startActivity(intent);
    }
}
