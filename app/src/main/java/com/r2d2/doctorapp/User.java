package com.r2d2.doctorapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class User {
    private String firstName;
    private String lastName;
    private Calendar birthday;
    private int sin;
    private String gender;

    public User(String firstName, String lastName, int birthyear, int birthmonth, int birthdate,
                int sin, String gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = new GregorianCalendar();
        this.birthday.set(birthyear, birthmonth, birthdate);
        this.sin = sin;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        User u = (User)obj;
        return this.sin == u.sin;
    }

    @Override
    public int hashCode() {
        return this.sin;
    }

    @Override
    public String toString() {
        return this.firstName + ", " +this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getSin() {
        return sin;
    }

    public void setSin(int sin) {
        this.sin = sin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
