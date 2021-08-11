package com.r2d2.doctorapp;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

}
