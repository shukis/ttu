package com.example.pavel.foodel1;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import com.example.pavel.foodel1.SingIn;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;

@RunWith(MockitoJUnitRunner.class)


public class SingInTest {
    @Mock
    Context fakeContext;

    @Mock
    Resources fakeRes;

    @Mock
    RegistrationForm reg;


    @Test
    public void testing() {
        //when(reg.getStName()).thenReturn("Valja");
        //when(reg.getStAddress()).thenReturn("lala");
        //when(reg.getStCity()).thenReturn("dfd");
        //when(reg.getStSurname()).thenReturn("dfdf");
        assertEquals(false,reg.checkEntryFields());





    }
}