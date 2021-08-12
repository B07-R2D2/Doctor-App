package com.r2d2.doctorapp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PatientTest {

    @Mock
    private FirebaseDatabase db;
    @Mock
    private DatabaseReference ref;

    private Patient p1;

    @Before
    public void before() {
        when(db.getReference(anyString())).thenReturn(ref);
        when(ref.child(anyString())).thenReturn(ref);

        p1 = new Patient(db, "bobby");
        p1.setFirstName("Bob");
        p1.setLastName("Williker");
        p1.setPassword("1234");
        p1.setGender("Male");
        p1.setSin(123456789);
        p1.setMedicalCondition("joint pain");
    }

    @Test
    public void testEqual()
    {
        assertEquals(p1, p1);
    }
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testDifferentObjectEqual()
    {
        assertNotEquals(123, p1);
    }
    @Test
    public void testNullEqual()
    {
        assertNotEquals(null, p1);
    }
    @Test
    public void testHashCode() {
        assertEquals(p1.hashCode(), 123456789);
    }

    @Test
    public void testToString() {
        assertEquals(p1.toString(), "Patient Bob Williker");
    }

    @Test
    public void testGetMedicalCondition() {
        assertEquals(p1.getProfile().getMedicalCondition(), "joint pain");
    }

}
