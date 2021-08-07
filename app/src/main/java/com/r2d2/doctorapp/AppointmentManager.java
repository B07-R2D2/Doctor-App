package com.r2d2.doctorapp;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/*
    I think we'll eventually have a doctor and patient object if we want to add an appointment to patient,
    but I'm not sure (could be modified to take in String doctorName and patientName instead)

    use AppointmentManager when you want to add/remove an appointment to/from a Patient

    to add an appointment to patient:
        AppointmentManager man = new AppointmentManager(doctor, patient, timeSlot)  // shouldnt be null, timeSlot is int
        man.addAppointmentToPatient();

    to remove an appointment from patient:
        AppointmentManager man = new AppointmentManager(doctor, patient, timeSlot)  // shouldnt be null, timeSlot is int
        man.removeAppointmentFromPatient();

*/

public class AppointmentManager {
    Doctor doctor;
    Patient patient;
    int timeSlot;
    DatabaseReference doctor_ref;       // want to keep all firebase related code inside this class
    DatabaseReference patient_ref;
    Appointment appointment;            // appointment to be arranged

    public AppointmentManager(Doctor doctor, Patient patient, int timeSlot) {
        this.timeSlot = timeSlot;
        this.doctor = doctor;
        this.patient = patient;
        this.doctor_ref = FirebaseDatabase.getInstance().getReference("Doctors").child(doctor.getUsername());
        this.patient_ref = FirebaseDatabase.getInstance().getReference("Patients").child(patient.getUsername());
        this.appointment = new Appointment(doctor.getUsername(), patient.getUsername(), timeSlot);
    }

    /*
    public void addAppointmentToPatient() {
        if (!this.appointment.getDoctorName().equals(doctor.getUsername())) {
            // handle error when appointment's doctorName != doctor's userName
            Log.i("Appointment", "appointment's doctorName != doctor's userName");
            return;
        }
        // make the appointment's patientName to be "" so that we could find the appointment in Doctor
        this.appointment.setPatientName("");

        // loop though free appointmnets, find the timeSlot (by finding the same timeSlot int value),
        // change patientName to the patient's name, and add the timeslot to patient and patient's database
        for (Appointment a : doctor.getFreeAppointments()) {
            if (a.timeSlot == appointment.timeSlot)) {
//                int index = a.getIndex();
//                doctor.getAppointments().index(index).setPatient(patient.getUsername());

                // update the new
            }
        }

        // if didn't find the appointment then mark as error

    }
    */


    // in patient:  remove timeslot, in object and database
    // in doctor: let the patientName field inside the changed appointment to be "". update to database.
    public void removeAppointmentFromPatient() {

    }




}
