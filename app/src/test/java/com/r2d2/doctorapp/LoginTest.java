package com.r2d2.doctorapp;


import android.content.Intent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
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
    @Mock
    LoginModel model;

    @Test
    public void testPresenterNotFound()
    {
        LoginPresenter p = new LoginPresenter(model,view);
        when(model.checkLogin("k123","k333")).thenReturn(0);
        p.checkLogin("k123","k333");
        verify(view).displayMessage();
    }
    @Test
    public void testPresenterPatient()
    {
        LoginPresenter p = new LoginPresenter(model,view);
        when(model.checkLogin("k","k")).thenReturn(1);
        p.checkLogin("k","k");
        verify(view).startActivity(any());
    }
    @Test
    public void testPresenterDoctor()
    {
        LoginPresenter p = new LoginPresenter(model,view);
        when(model.checkLogin("k2","k2")).thenReturn(2);
        p.checkLogin("k2","k2");
        verify(view).startActivity(any());
    }
}
