package com.r2d2.doctorapp;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class LoginModelTest {

    @Mock
    private FirebaseDatabase db;
    @Mock
    private DatabaseReference allUsersRef, allOtherUsersRef, userRef, otherUserRef;
    @Mock
    private DataSnapshot allUsersSnapshot, userSnapshot;

    @Test
    public void testListenForUsers() {
        doTestListenForUsers(
            new Patient.Profile("", "", "patient123", "password123", "other", 1, "", 1),
            "Patients", "Doctors",
            1
        );
        doTestListenForUsers(
            new Doctor.Profile("", "", "doctor123", "password123", "other", 1, new ArrayList<>(), "", "", 1, new ArrayList<>(Collections.singletonList("spec"))),
            "Doctors", "Patients",
            2
        );
    }

    private <Profile extends User.Profile> void doTestListenForUsers(Profile profile, String userTypeString, String otherUserTypeString, int userTypeNumber) {
        when(db.getReference(userTypeString)).thenReturn(allUsersRef);
        when(db.getReference(otherUserTypeString)).thenReturn(allOtherUsersRef);

        // Methods used in User
        when(allUsersRef.child(anyString())).thenReturn(userRef);
        when(allOtherUsersRef.child(anyString())).thenReturn(otherUserRef);
        when(userRef.addValueEventListener(any())).thenAnswer(invocation -> {
            ValueEventListener listener = (ValueEventListener) invocation.getArgument(0);
            if (listener != null) listener.onDataChange(userSnapshot);
            return null;
        });
        doReturn(profile).when(userSnapshot).getValue(profile.getClass());

        // Methods used in LoginModel
        when(allUsersSnapshot.getChildren()).thenReturn(Collections.singletonList(userSnapshot));
        when(userSnapshot.getKey()).thenReturn(profile.getUsername());
        when(allUsersRef.addValueEventListener(any())).thenAnswer(invocation -> {
            ValueEventListener listener = (ValueEventListener) invocation.getArgument(0);
            if (listener != null) listener.onDataChange(allUsersSnapshot);
            return null;
        });

        LoginModel model = new LoginModel(db);

        verify(db, atLeastOnce()).getReference("Patients");
        verify(db, atLeastOnce()).getReference("Doctors");

        // Correct username and password:
        assertEquals(userTypeNumber, model.checkLogin(profile.getUsername(), profile.getPassword()));
        // Incorrect password:
        assertEquals(0, model.checkLogin(profile.getUsername(), "wrong_password"));
        // Nonexistent account:
        assertEquals(0, model.checkLogin("unknown_username", "wrong_password"));
    }

}
