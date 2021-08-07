package com.r2d2.doctorapp;


/*
    reason for using strings instead of Doctor and Patient class: make it easier to save in firebase

    add field "ArrayList<Appointment> appointments" to user
        Init in Doctor:
        appointments[8] = {
            doctoName = doctor's name
            patientName = ""            // this appointment not booked yet
            timeSlot = i                // where 9 <= i <= 16
        } (for each appointment)

        Init in Patient:
        ArrayList<Appointment> appointments = new List<>();

        add Functions to Doctor:
            ArrayList<Appointment> getFreeAppointments();        // returns a list of free appointments (ie. patientName = "")
            ArrayList<Appointment> getBookedAppointments();      // returns a list of appointments (ie. patientName != "")
                                                                    for user story 4
            Appointment getNextAppointment();              // returns the next Appointment
                                                              (ie. getAppointmentWithMinTimeSlot(getAppointments())
                                                               for user story 3
        add functions to Patient:
            ArrayList<Appointment> getAppointments();           // returns appointments field in Patient

 */

public class Appointment {
    String doctorName, patientName;
    int timeSlot;

    public Appointment(String doctorName, String patientName, int timeSlot) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.timeSlot = timeSlot;
    }

    public String timeSlotText() {
        return timeSlot + " : 00 ~ " + (timeSlot + 1) + " : 00";
    }



    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Appointment(int timeSlot) {
        this.timeSlot = timeSlot;
    }
}
