package com.example.pavel.foodel1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegistrationFormTest {
    @Test
    public void testCheckPasswordTrue() throws Exception {
        RegistrationForm rf = new RegistrationForm();
        boolean result = rf.checkPasswords("olalalala", "olalalala");
        assertTrue(result);
    }

    @Test
    public void testCheckPasswordFalse() {
        RegistrationForm rf = new RegistrationForm();
        boolean result = rf.checkPasswords("olalalalalalala", "olalalalala");
        assertFalse(result);
    }

    @Test
    public void testCheckCorrectEmailTrue(){
        RegistrationForm rf = new RegistrationForm();
        boolean result = rf.checkCorrectEmail("test@test.ru");
        assertTrue(result);
    }
    @Test
    public void testCheckCorrectEmailFalse(){
        RegistrationForm rf = new RegistrationForm();
        boolean result = rf.checkCorrectEmail("test.ru");
        assertFalse(result);
    }
}