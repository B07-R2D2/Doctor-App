package com.r2d2.doctorapp;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


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
    Doctor.Profile doctor;
    Patient.Profile patient;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    long timeStamp;

    Appointment appointment;            // appointment to be arranged

    public AppointmentManager(Doctor.Profile doctor, Patient.Profile patient, long timeStamp) {
        this.timeStamp = timeStamp;
        this.doctor = doctor;
        this.patient = patient;
        this.appointment = new Appointment(doctor.getUsername(), patient.getUsername(), timeStamp);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addAppointmentToPatient() {
        if (!this.appointment.getDoctorName().equals(doctor.getUsername())) {
            Log.i("Appointment", "appointment's doctorName != doctor's userName");
            return;
        }
        // make the appointment's patientName to be "" so that we could find the appointment in Doctor
        this.appointment.setPatientName("");

        // loop though free appointmnets, find the timeSlot (by finding the same timeSlot int value),
        // change patientName to the patient's name, and add the timeslot to patient and patient's database

        ArrayList<Appointment> newDoctorList = new ArrayList<>(doctor.getAppointments());
        ArrayList<Appointment> newPatientList = new ArrayList<>(patient.getAppointments());
        for (Appointment a: newDoctorList) {
            if (a.getTimeStamp() == appointment.getTimeStamp()) {
                if (!a.getPatientName().equals("")) {
                    Log.i("Appointment", "the appointment you want to add is already booked!");
                    return;
                }

                a.setPatientName(patient.getUsername());
                newPatientList.add(a);
                break;
            }
        }
        // set appointment of doctor & patient to newDoctorList & newPatientList
        Doctor newDoc = new Doctor(db, doctor.getUsername(), doctor);
        newDoc.setAppointments(newDoctorList);
        Patient newPat = new Patient(db, patient);
        newPat.setAppointments(newPatientList);
    }

    public void removeAppointmentFromPatient(){
        if (!this.appointment.getDoctorName().equals(doctor.getUsername())) {
            Log.i("Appointment", "appointment's doctorName != doctor's userName");
            return;
        } else if (!this.appointment.getPatientName().equals(patient.getUsername())) {
            Log.i("Appointment", "appointment's patientName != patient's userName");
            return;
        }

        // create new lists
        // loop through patient's appointments, check patient has booked (timestamp and doctor's name)
        // remove if find. return if not.
        // loop through doctor's appointments, check appointment.
        ArrayList<Appointment> newDoctorList = new ArrayList<>(doctor.getAppointments());
        ArrayList<Appointment> newPatientList = new ArrayList<>(patient.getAppointments());
        if (newPatientList.contains(appointment) && newDoctorList.contains(appointment)) {
            newPatientList.remove(appointment);
            for (Appointment a: newDoctorList) {
                if (a.getTimeStamp() == appointment.getTimeStamp()) {
                    if (a.getPatientName().equals("")) {
                        Log.i("Appointment", "Appointment already removed");
                        return;
                    }

                    a.setPatientName("");
                    break;
                }
            }

            // set appointment of doctor & patient to newDoctorList & newPatientList
            Doctor newDoc = new Doctor(db, doctor.getUsername(), doctor);
            newDoc.setAppointments(newDoctorList);
            Patient newPat = new Patient(db, patient);
            newPat.setAppointments(newPatientList);
        } else {
            Log.i("Appointment", "Either patient or doctor doesn't have this appointment");
        }
    }
}
