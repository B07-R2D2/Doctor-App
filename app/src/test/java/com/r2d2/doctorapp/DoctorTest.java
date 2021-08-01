package com.r2d2.doctorapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoctorTest {

    Doctor d1 = new Doctor("Jason", "Hou", 2000, 9, 6, 123456789,
            "Male", "Good Doctor", "University of Toronto", 123466,
            "Psychology");

//    Doctor d2 = new Doctor("Angus", "Lee", 2000, 10, 31,987654321,
//            "Male", "Motivated doctor", "University of Toronto", 334345, "Neuroscience");


    @Test
    public void testEquals1() {
        assertEquals(d1.equals(null), false);
    }
    @Test
    public void testEquals2() {
        int i = 0;
        assertEquals(d1.equals(i), false);
    }
    @Test
    public void testEquals3() {
        User u3 = new Doctor("Jason", "Hou", 2000, 9, 6, 123456789,
                "Male", "Good Doctor", "University of Toronto", 123466,
                "Psychology");
        assertEquals(d1.equals(u3), true);
    }

    @Test
    public void testHashCode() {
        assertEquals(d1.hashCode(), 123456789);
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString(), "Dr. Jason, Hou");
    }

    @Test
    public void testGetBio() {
        assertEquals(d1.getBio(), "Good Doctor");
    }

    @Test
    public void testSetBio() {
        d1.setBio("Very Good Doctor");
        assertEquals(d1.getBio(), "Very Good Doctor");
    }

    @Test
    public void testGetUni() {
        assertEquals(d1.getUni(), "University of Toronto");
    }

    @Test
    public void testSetUni() {
        d1.setUni("UTSC");
        assertEquals(d1.getUni(), "UTSC");
    }

    @Test
    public void testGetDoctorId() {
        assertEquals(d1.getDoctorId(), 123466);
    }

    @Test
    public void testSetDoctorId() {
        d1.setDoctorId(121121);
        assertEquals(d1.getDoctorId(), 121121);
    }
}