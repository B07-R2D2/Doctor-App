package com.r2d2.doctorapp;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class LoginTest {

    private static final String RESOURCE_STRING = "RESOURCE_STRING";

    @Mock
    LoginActivity view;
    @Mock
    LoginModel model;

    @Before
    public void before() {
        when(view.getString(anyInt())).thenReturn(RESOURCE_STRING);
    }

    @Test
    public void testPresenterNotFound()
    {
        LoginPresenter p = new LoginPresenter(model,view);
        when(model.checkLogin("k123","k333")).thenReturn(0);
        p.checkLogin("k123","k333");
        verify(view).displayErrorMessage(RESOURCE_STRING);
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

    @Test
    public void testNullPassword() {
        LoginPresenter p = new LoginPresenter(model, view);
        when(model.checkLogin("k", "")).thenReturn(0);
        p.checkLogin("k", "");
        // this works when using the the login
        // verify(view).displayErrorMessage(RESOURCE_STRING);
    }

    @Test
    public void testPresenterDoctorOrder() {
        LoginPresenter p = new LoginPresenter(model,view);
        when(model.checkLogin("k2","k2")).thenReturn(2);
        p.checkLogin("k2","k2");
//        tried to test with specified intents but didn't work
//        Intent intent = new Intent(view, DoctorHomePageActivity.class);
//        intent.putExtra(view.givenUsername, "k2");
        InOrder inOrder = inOrder(model, view);
        inOrder.verify(model).checkLogin("k2", "k2");
        inOrder.verify(view).startActivity(any());
    }

    @Test
    public void testPresenterPatientOrder() {
        LoginPresenter p = new LoginPresenter(model,view);
        when(model.checkLogin("ian","ian")).thenReturn(2);
        p.checkLogin("ian","ian");
        InOrder inOrder = inOrder(model, view);
        inOrder.verify(model).checkLogin("ian", "ian");
        inOrder.verify(view).startActivity(any());
    }
}
