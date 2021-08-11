package com.r2d2.doctorapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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

    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

    public String getDateOfBirth() {
        return dateFormat.format(new Date(patientProfile.getDateOfBirth()));
    }

    public String getMedicalCondition() {
        return patientProfile.getMedicalCondition();
    }

    public void fetchPastAppointmentInfo(Consumer<List<String>> callback) {
        AppointmentInfo.fetchAppointmentInfo(patientProfile.getPastAppointments(), callback);
    }

}
