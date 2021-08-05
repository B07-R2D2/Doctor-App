package com.r2d2.doctorapp;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientTest {
    //Patient(String firstName, String lastName,String username, String password, String gender, int sin, String medical)
    Patient p1 = new Patient("Bob","Williker",
            "bobby","1234","Male",123456789,"joint pain");
    @Test
    public void testEqual()
    {
        Patient p2 = new Patient("Bob","Williker",
                "bobby","1234","Male",123456789,"joint pain");
        assertEquals(p1.equals(p2),true);
    }
    @Test
    public void testDifferentObjectEqual()
    {
        assertEquals(p1.equals(123),false);
    }
    @Test
    public void testNullEqual()
    {
        assertEquals(p1.equals(null),false);
    }
    @Test
    public void testHashCode() {
        assertEquals(p1.hashCode(), 123456789);
    }

    @Test
    public void testToString() {
        assertEquals(p1.toString(), "Patient Bob, Williker");
    }

    @Test
    public void testGetMedicalCondition() {
        assertEquals(p1.getMedicalCondition(), "joint pain");
    }

    @Test
    public void testSetMedicalCondition() {
        p1.setMedicalCondition("hearing loss");
        assertEquals(p1.getMedicalCondition(), "hearing loss");
    }


}
