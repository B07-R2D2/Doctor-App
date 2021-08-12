package com.r2d2.doctorapp;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AppointmentInfo {

    private static final String LOG_TAG = "AppointmentInfo";

    public static void fetchAppointmentInfo(List<Appointment> appointments, Consumer<List<String>> callback) {
        int appointmentsCount = appointments.size();

        // Android Studio recommended this clever hack:
        // use an int[1] as a mutable boxed int.
        int[] fetchedCount = {0};
        List<String> appointmentInfo = new ArrayList<>();

        for (Appointment appointment : appointments) {
            Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), appointment.getDoctorName());
            doctor.addOneTimeObserver(() -> {
                fetchedCount[0]++;
                appointmentInfo.add(doctor + "\n" + appointment);
                Log.d(LOG_TAG, "Fetched appointment: " + appointmentInfo.get(appointmentInfo.size() - 1));

                if (fetchedCount[0] == appointmentsCount) {
                    callback.accept(appointmentInfo);
                }
            });
        }
    }

}
