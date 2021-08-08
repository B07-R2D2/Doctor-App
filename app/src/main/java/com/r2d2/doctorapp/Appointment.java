package com.r2d2.doctorapp;

/*
    bugs:
         - doctor's name wasn't initialized in Appointment
         = When we finish booking a timeslot and go back to filter activity, we'll see another identical
           item in the recycler view. (Probably has something to do with AppointmentManager where I created
           a new Doctor to gain access to the setter of appointment
         - the date is correctly initialized but not correctly displayed. (they all display the
           same value. The cause might be that timeSlotText() in Appointment class isn't working. (Note
           I'm pretty sure the appointments are distinct, bc it shows different timestamps on patient's side
           when we book an appointment.)
         -
 */



/*
    reason for using strings instead of Doctor and Patient class: make it easier to save in firebase

    add field "ArrayList<Appointment> appointments" to user
        Init in Doctor:
        appointments[7][8] = {          // 7 days a week, 8 timeslots a day
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

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Appointment implements Serializable {
    private String doctorName, patientName;
    private long timeStamp;

    public Appointment(String doctorName, String patientName, long timeSlot) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.timeStamp = timeSlot;
    }

    public Appointment() {
        this.doctorName = "";
        this.patientName = "";
        this.timeStamp = -1;
    }

    public String timeSlotText() {
        Date d = new Date(timeStamp);
        return d.getMonth() + "/" + d.getDate() + " " + d.getHours() + ":00 ~ " + (d.getHours() + 1) + ":00";
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return timeStamp == that.timeStamp && Objects.equals(doctorName, that.doctorName) && Objects.equals(patientName, that.patientName);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(doctorName, patientName, timeStamp);
    }
}
