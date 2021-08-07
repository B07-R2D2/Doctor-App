package com.r2d2.doctorapp;

import android.content.Intent;

public class PatientHome {

    private final Patient patient;
    private final PatientHomeActivity view;

    public PatientHome(Patient patient, PatientHomeActivity view) {
        this.patient = patient;
        this.view = view;
    }

    public void goToDoctorHistory() {
        Intent intent = new Intent(view, DoctorHistoryActivity.class);
        intent.putExtra(DoctorHistoryActivity.EXTRA_PATIENT_PROFILE, patient.getProfile());
        view.startActivity(intent);
    }

    public void goToPatientProfile() {
        Intent intent = new Intent(view, PatientProfileActivity.class);
        intent.putExtra(PatientProfileActivity.EXTRA_PATIENT_PROFILE, patient.getProfile());
        view.startActivity(intent);
    }

}
