package com.r2d2.doctorapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Tests PatientHome. */
@RunWith(MockitoJUnitRunner.class)
public class PatientHomeTest {

    @Mock
    Patient patient;

    @Mock
    PatientHomeActivity view;

    @Test
    public void testGoToFindSpecialist() {
        PatientHome presenter = new PatientHome(patient, view);
        presenter.goToFindSpecialist();
        verify(view).startActivity(any());
    }

    @Test
    public void testGoToPatientProfile() {
        PatientHome presenter = new PatientHome(patient, view);
        presenter.goToPatientProfile();
        verify(view).startActivity(any());
    }

}