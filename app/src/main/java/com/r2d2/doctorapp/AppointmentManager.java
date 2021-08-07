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


    public void addAppointmentToPatient() {
        if (!this.appointment.getDoctorName().equals(doctor.getUsername())) {
            Log.i("Appointment", "appointment's doctorName != doctor's userName");
            return;
        }
        // make the appointment's patientName to be "" so that we could find the appointment in Doctor
        this.appointment.setPatientName("");

        // loop though free appointmnets, find the timeSlot (by finding the same timeSlot int value),
        // change patientName to the patient's name, and add the timeslot to patient and patient's database
        boolean isAppointmentFree = false;
        for (Appointment a : doctor.getFreeAppointments()) {
            if (a.getTimeSlot() == appointment.getTimeSlot()) {
                isAppointmentFree = true;
                // not sure if this will modify the appointment
                doctor.getAppointments().get(a.getIndex()).setPatientName(patient.getUsername());
                Appointment newAppointment = new Appointment(doctor.getUsername(), patient.getUsername(), a.getTimeSlot());
                patient.getAppointments().add(newAppointment);
                doctor_ref.setValue(doctor);
                patient_ref.setValue(patient);
                break;
            }
        }
        // if didn't find the appointment then mark as error
        if (!isAppointmentFree) {
            Log.i("Appointment", "the appointment you want to add is already booked!");
        }
    }



    // in patient:  remove timeslot, in object and database
    // in doctor: let the patientName field inside the changed appointment to be "". update to database.
    public void removeAppointmentFromPatient() {
        if (!this.appointment.getDoctorName().equals(doctor.getUsername())) {
            Log.i("Appointment", "appointment's doctorName != doctor's userName");
            return;
        } else if (!this.appointment.getPatientName().equals(patient.getUsername())) {
            Log.i("Appointment", "appointment's patientName != patient's userName");
            return;
        }

        // loop though free appointmnets, find the timeSlot (by finding the same timeSlot int value),
        // change patientName to the patient's name, and add the timeslot to patient and patient's database
        boolean removedAppointment = false;
        for (Appointment a : patient.getAppointments()) {
            if (a.getTimeSlot() == appointment.getTimeSlot() && a.getDoctorName().equals(appointment.getDoctorName())) {
                removedAppointment = true;
                // not sure if this will modify the appointment
                patient.getAppointments().remove(a);
                doctor.getAppointments().get(a.getIndex()).setPatientName("");
                doctor_ref.setValue(doctor);
                patient_ref.setValue(patient);
                break;
            }
        }
        // if didn't find the appointment then mark as error
        if (!removedAppointment) {
            Log.i("Appointment", "the appointment you want to remove is not found!");
        }
    }

}
