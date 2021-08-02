package com.r2d2.doctorapp;

import android.icu.text.DateFormat;

import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class UserUnitTest {

    User u1 = new Doctor("Jason", "Hou", "Jasonnnn","54354353", 123456789,
            "Male", "Good Doctor", "University of Toronto", 123466,
            "Psychology");
    @Test
    public void testEquals1() {
        assertEquals(u1.equals(null), false);
    }
    @Test
    public void testEquals2() {
        int i = 0;
        assertEquals(u1.equals(i), false);
    }
    @Test
    public void testEquals3() {
        User u3 = new Doctor("Jason", "Hou", "Jasonnnn","54354353", 123456789,
                "Male", "Good Doctor", "University of Toronto", 123466,
                "Psychology");
        assertEquals(u1.equals(u3), true);
    }

    @Test
    public void testHashCode() {
        assertEquals(u1.hashCode(), 123456789);
    }

    @Test
    public void testToString() {

        assertEquals(u1.toString(), "Dr. Jason, Hou");
    }

    @Test
    public void testGetFirstName() {
        assertEquals(u1.getFirstName(), "Jason");
    }

    @Test
    public void testSetFirstName() {
        u1.setFirstName("Angus");
        assertEquals(u1.getFirstName(), "Angus");
    }

    @Test
    public void testGetLastName() {
        assertEquals(u1.getLastName(), "Hou");
    }

    @Test
    public void testSetLastName() {
        u1.setLastName("Lee");
        assertEquals(u1.getLastName(), "Lee");
    }

    @Test
    public void testGetSin() {
        assertEquals(u1.getSin(), 123456789);
    }

    @Test
    public void testSetSin() {
        u1.setSin(987654321);
        assertEquals(u1.getSin(), 987654321);
    }

    @Test
    public void testGetGender() {
        assertEquals(u1.getGender(), "Male");
    }

    @Test
    public void testSetGender() {
        u1.setGender("Female");
        assertEquals(u1.getGender(), "Female");
    }
}