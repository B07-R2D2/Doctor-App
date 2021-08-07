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
public class UserUnitTest {

    @Mock
    private FirebaseDatabase db;
    @Mock
    private DatabaseReference ref;

    private User u1;

    @Before
    public void before() {
        when(db.getReference(anyString())).thenReturn(ref);
        when(ref.child(anyString())).thenReturn(ref);
        when(ref.getDatabase()).thenReturn(db);

        u1 = new Doctor(db, "Jasonnnn");
        u1.setFirstName("Jason");
        u1.setLastName("Hou");
        u1.setPassword("54354353");
        u1.setSin(123456789);
        u1.setGender("Male");
        u1.setSin(123456789);
    }

    @Test
    public void testEquals1() {
        assertNotEquals(null, u1);
    }
    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testEquals2() {
        int i = 0;
        assertNotEquals(u1, i);
    }
    @Test
    public void testEquals3() {
        assertEquals(u1, u1);
    }

    @Test
    public void testHashCode() {
        assertEquals(123456789, u1.hashCode());
    }

    @Test
    public void testToString() {

        assertEquals("Dr. Jason, Hou", u1.toString());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Jason", u1.getProfile().getFirstName());
    }

    @Test
    public void testSetFirstName() {
        u1.setFirstName("Angus");
        assertEquals("Angus", u1.getProfile().getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Hou", u1.getProfile().getLastName());
    }

    @Test
    public void testSetLastName() {
        u1.setLastName("Lee");
        assertEquals("Lee", u1.getProfile().getLastName());
    }

    @Test
    public void testGetSin() {
        assertEquals(123456789, u1.getProfile().getSin());
    }

    @Test
    public void testSetSin() {
        u1.setSin(987654321);
        assertEquals(987654321, u1.getProfile().getSin());
    }

    @Test
    public void testGetGender() {
        assertEquals("Male", u1.getProfile().getGender());
    }

    @Test
    public void testSetGender() {
        u1.setGender("Female");
        assertEquals("Female", u1.getProfile().getGender());
    }
}