package com.r2d2.doctorapp;


import android.content.Intent;
import android.util.Log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class LoginTest {
    @Mock
    LoginActivity view;
    @Test
    public void testPresenter()
    {
        LoginPresenter p = new LoginPresenter(view.m,view);
        p.checkLogin("k2","k3");
        verify(view).displayMessage();
    }
    public void testPresenterPatient()
    {
        LoginPresenter p = new LoginPresenter(view.m,view);
        p.checkLogin("k","k");
        Intent intent = new Intent(view, PatientHomeActivity.class);
        verify(view).startActivity(intent);
    }
    public void testPresenterDoctor()
    {
        LoginPresenter p = new LoginPresenter(view.m,view);
        p.checkLogin("k","k");
        Intent intent = new Intent(view, DoctorHomePageActivity.class);
        verify(view).startActivity(intent);
    }
}
