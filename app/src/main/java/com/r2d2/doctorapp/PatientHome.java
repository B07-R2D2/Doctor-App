package com.r2d2.doctorapp;

import android.content.Intent;

public class PatientHome {

    private final Patient patient;
    private final PatientHomeActivity view;

    public PatientHome(Patient patient, PatientHomeActivity view) {
        this.patient = patient;
        this.view = view;
    }

    public void goToFindSpecialist() {
        Intent intent = new Intent(view, FilterActivity.class);
        // TODO: Push patient profile or similar as extra
        view.startActivity(intent);
    }

    public void goToPatientProfile() {
        Intent intent = new Intent(view, PatientProfileActivity.class);
        intent.putExtra(PatientProfileActivity.EXTRA_PATIENT_PROFILE, patient.getProfile());
        view.startActivity(intent);
    }

}
