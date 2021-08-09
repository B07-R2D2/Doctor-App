package com.r2d2.doctorapp;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DoctorHistory {

    private final Patient.Profile patient;
    private final DoctorHistoryActivity view;

    public DoctorHistory(Patient.Profile patient, DoctorHistoryActivity view) {
        this.patient = patient;
        this.view = view;
    }

    public void fetchDoctors(Consumer<List<Doctor.Profile>> callback) {
        List<String> usernames = patient.getDoctorHistory();
        int usernamesCount = usernames.size();

        // Android Studio recommended this clever hack:
        // use an int[1] as a mutable boxed int.
        int[] fetchedCount = {0};
        List<Doctor.Profile> doctors = new ArrayList<>();
        for (String username : usernames) {
            Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), username);
            doctor.addOneTimeObserver(() -> {
                fetchedCount[0]++;
                doctors.add(doctor.getProfile());

                if (fetchedCount[0] == usernamesCount) {
                    callback.accept(doctors);
                }
            });
        }
    }

}
