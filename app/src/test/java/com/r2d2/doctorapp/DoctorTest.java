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
public class DoctorTest {

    @Mock
    private FirebaseDatabase db;
    @Mock
    private DatabaseReference ref;

    private Doctor d1;

    @Before
    public void before() {
        when(db.getReference(anyString())).thenReturn(ref);
        when(ref.child(anyString())).thenReturn(ref);
        when(ref.getDatabase()).thenReturn(db);

        d1 = new Doctor(db, "Jasonnnn");
        d1.setFirstName("Jason");
        d1.setLastName("Hou");
        d1.setPassword("54354353");
        d1.setSin(123456789);
        d1.setGender("Male");
        d1.setSin(123456789);
        d1.setBio("Good Doctor");
        d1.setUni("University of Toronto");
        d1.setDoctorId(123466);
        d1.setSpecialization("Psychology");
    }

    @Test
    public void testEquals1() {
        assertNotEquals(null, d1);
    }
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testEquals2() {
        int i = 0;
        assertNotEquals(d1, i);
    }
    @Test
    public void testEquals3() {
        assertEquals(d1, d1);
    }

    @Test
    public void testHashCode() {
        assertEquals(123456789, d1.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Dr. Jason, Hou", d1.toString());
    }

    @Test
    public void testGetBio() {
        assertEquals("Good Doctor", d1.getProfile().getBio());
    }

    @Test
    public void testSetBio() {
        d1.setBio("Very Good Doctor");
        assertEquals("Very Good Doctor", d1.getProfile().getBio());
    }

    @Test
    public void testGetUni() {
        assertEquals("University of Toronto", d1.getProfile().getUni());
    }

    @Test
    public void testSetUni() {
        d1.setUni("UTSC");
        assertEquals("UTSC", d1.getProfile().getUni());
    }

    @Test
    public void testGetDoctorId() {
        assertEquals(123466, d1.getProfile().getDoctorId());
    }

    @Test
    public void testSetDoctorId() {
        d1.setDoctorId(121121);
        assertEquals(121121, d1.getProfile().getDoctorId());
    }
}