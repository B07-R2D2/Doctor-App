package com.r2d2.doctorapp;
import java.util.Date;

public class Patient extends User{
    private String medicalCondition;
    private String username;
    private int patientID;
    private schedule patientSchedule;

        public Patient(String firstName, String lastName, Date birthday, int sin, String medical, String username, int patientId) {
            super(firstName,lastName,birthday,sin);
            this.medicalCondition = medical;
            this.username = username;
            this.patientID = patientId;
        }
    }
}
