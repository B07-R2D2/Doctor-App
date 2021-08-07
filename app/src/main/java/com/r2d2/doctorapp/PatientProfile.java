package com.r2d2.doctorapp;

public class PatientProfile {

    private final Patient.Profile patientProfile;
    private final PatientProfileActivity view;

    public PatientProfile(Patient.Profile patientProfile, PatientProfileActivity view) {
        this.patientProfile = patientProfile;
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

}
