package com.r2d2.doctorapp;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AppointmentHistory {

    private final Patient.Profile patient;
    private final AppointmentHistoryActivity view;

    public AppointmentHistory(Patient.Profile patient, AppointmentHistoryActivity view) {
        this.patient = patient;
        this.view = view;
    }

    public void fetchAppointmentInfo(Consumer<List<String>> callback) {
        List<Appointment> appointments = patient.getPastAppointments();
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

                if (fetchedCount[0] == appointmentsCount) {
                    callback.accept(appointmentInfo);
                }
            });
        }
    }

}
