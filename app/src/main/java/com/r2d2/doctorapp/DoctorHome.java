package com.r2d2.doctorapp;

import android.content.Intent;
import android.view.View;

public class DoctorHome {

    private final Doctor doctor;
    private final DoctorHomePageActivity view;

    public DoctorHome(Doctor doctor, DoctorHomePageActivity view) {
        this.doctor = doctor;
        this.view = view;
    }
    public void findPatientInfo(){
        Intent intent = new Intent(view, PatientProfileActivity.class);
        view.startActivity(intent);
    }
}
