package com.r2d2.doctorapp;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctorCalendar {

    private static final String LOG_TAG = "DoctorCalendarActivity";

    private List<Appointment> appointments;
    private List<Patient.Profile> patientProfiles;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Patient.Profile> getPatientProfiles() {
        return patientProfiles;
    }

    private int fetchedCount, totalCount;

    private Runnable callback;

    public void fetchAppointments(String doctorUsername, Runnable callback) {
        this.callback = callback;

        appointments = new ArrayList<>();
        patientProfiles = new ArrayList<>();

        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), doctorUsername);
        doctor.addOneTimeObserver(() -> {
            appointments = doctor.getProfile().getAppointments();

            fetchedCount = 0;
            totalCount = appointments.size();
            Log.d(LOG_TAG, "Doctor has " + totalCount + " appointments");

            for (Appointment appointment : appointments) {
                String patientName = appointment.getPatientName();
                if (patientName.isEmpty()) {
                    patientProfiles.add(null);
                    checkIfFetchedAll();
                } else {
                    Patient patient = new Patient(doctor.getRef().getDatabase(), appointment.getPatientName());
                    patient.addOneTimeObserver(() -> {
                        patientProfiles.add(patient.getProfile());
                        checkIfFetchedAll();
                    });
                }
            }
        });
    }

    private void checkIfFetchedAll() {
        fetchedCount++;
        Log.d(LOG_TAG, "Fetched " + fetchedCount + " of " + totalCount);
        if (fetchedCount == totalCount) {
            // We've fetched everything.
            callback.run();
        }
    }

}
