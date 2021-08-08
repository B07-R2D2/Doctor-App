package com.r2d2.doctorapp;

import android.content.Intent;

public class PatientProfile {

    private final Patient.Profile patientProfile;
    private final PatientProfileActivity view;

    public PatientProfile(Patient.Profile patientProfile, PatientProfileActivity view) {
        this.patientProfile = (patientProfile != null) ? patientProfile : new Patient.Profile();
        this.view = view;
    }

    public String getFullName() {
        return patientProfile.getFirstName() + " " + patientProfile.getLastName();
    }

    public String getGender() {
        return patientProfile.getGender();
    }

    public String getMedicalCondition() {
        return patientProfile.getMedicalCondition();
    }

    public void goToDoctorHistory() {
        Intent intent = new Intent(view, DoctorHistoryActivity.class);
        intent.putExtra(DoctorHistoryActivity.EXTRA_PATIENT_PROFILE, patientProfile);
        view.startActivity(intent);
    }

}
