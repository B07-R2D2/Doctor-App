package com.r2d2.doctorapp;


import java.util.Date;


public abstract class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private int sin;

    public User(){

    }

    public User(String firstName, String lastName,String username,String password, String gender, int sin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.password = password;
        this.gender = gender;
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
    
    public String getUsername() {
        return userName;
    }
    
    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

   
}
