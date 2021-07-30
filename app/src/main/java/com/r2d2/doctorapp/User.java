package com.r2d2.doctorapp;
import java.util.Date;

public abstract class User {
    private String firstName;
    private String lastName;
    private Date birthday;
    private int sin;

    public User(String firstName, String lastName, Date birthday, int sin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sin = sin;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSin() {
        return sin;
    }

    public void setSin(int sin) {
        this.sin = sin;
    }
}