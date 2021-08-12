package com.r2d2.doctorapp;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorCalendar {

    private static final String LOG_TAG = "DoctorCalendarActivity";

    private List<Appointment> appointments;
    private Map<String, Patient.Profile> patientProfiles;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Map<String, Patient.Profile> getPatientProfiles() {
        return patientProfiles;
    }

    private int fetchedCount, totalCount;

    private Runnable callback;

    public void fetchAppointments(String doctorUsername, Runnable callback) {
        this.callback = callback;

        appointments = new ArrayList<>();
        patientProfiles = new HashMap<>();

        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), doctorUsername);
        doctor.addOneTimeObserver(() -> {
            appointments = doctor.getProfile().getAppointments();

            fetchedCount = 0;
            totalCount = appointments.size();
            Log.d(LOG_TAG, "Doctor has " + totalCount + " appointments");

            int i = 0;
            for (Appointment appointment : appointments) {
                i++;
                String patientName = appointment.getPatientName();
                if (patientName.isEmpty()) {
                    checkIfFetchedAll();
                } else {
                    Patient patient = new Patient(doctor.getRef().getDatabase(), appointment.getPatientName());
                    final int patientIndex = i; // Ensures consistent order
                    patient.addOneTimeObserver(() -> {
                        patientProfiles.put(patientName, patient.getProfile());
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
