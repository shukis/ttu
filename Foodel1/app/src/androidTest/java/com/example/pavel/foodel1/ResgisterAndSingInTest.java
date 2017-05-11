package com.example.pavel.foodel1;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ResgisterAndSingInTest {

    @Rule
    public ActivityTestRule<SingIn> mActivityTestRule = new ActivityTestRule<>(SingIn.class);

    @Test
    public void singInTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.signInRegistrationButton), withText("Sign Up"), isDisplayed()));
        button.perform(click());

        ViewInteraction editText = onView(
                withId(R.id.registrationEmail));
        editText.perform(scrollTo(), replaceText("testing@mail.com"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                withId(R.id.registrationName));
        editText2.perform(scrollTo(), replaceText("Roger"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.registrationName), withText("Roger")));
        editText3.perform(pressImeActionButton());

        ViewInteraction editText4 = onView(
                withId(R.id.registrationSurname));
        editText4.perform(scrollTo(), replaceText("Donovan"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.registrationSurname), withText("Donovan")));
        editText5.perform(pressImeActionButton());

        ViewInteraction editText6 = onView(
                withId(R.id.registrationCity));
        editText6.perform(scrollTo(), replaceText("Loksa"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.registrationCity), withText("Loksa")));
        editText7.perform(pressImeActionButton());

        ViewInteraction editText8 = onView(
                withId(R.id.registrationAddress));
        editText8.perform(scrollTo(), replaceText("Pae 28"), closeSoftKeyboard());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.registrationAddress), withText("Pae 28")));
        editText9.perform(pressImeActionButton());

        ViewInteraction editText10 = onView(
                withId(R.id.registrationPassword));
        editText10.perform(scrollTo(), replaceText("11111111"), closeSoftKeyboard());

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.registrationPassword), withText("11111111")));
        editText11.perform(pressImeActionButton());

        ViewInteraction editText12 = onView(
                withId(R.id.registrationCheckPassword));
        editText12.perform(scrollTo(), replaceText("11111111"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.registrationButton), withText("Sign Up")));
        button2.perform(scrollTo(), click());

        ViewInteraction autoCompleteTextView = onView(
                allOf(withId(R.id.signInEmail), isDisplayed()));
        autoCompleteTextView.perform(click());

        ViewInteraction autoCompleteTextView2 = onView(
                allOf(withId(R.id.signInEmail), isDisplayed()));
        autoCompleteTextView2.perform(replaceText("testing@mail.com"), closeSoftKeyboard());

        ViewInteraction editText13 = onView(
                allOf(withId(R.id.signInPassword), isDisplayed()));
        editText13.perform(replaceText("11111111"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.signInButton), withText("Sign In"), isDisplayed()));
        button3.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.actionProfile),  isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.actionDelete),  isDisplayed()));
        button5.perform(click());

        ViewInteraction editText14 = onView(
                allOf(withId(R.id.deletePassword), isDisplayed()));
        editText14.perform(replaceText("11111111"), closeSoftKeyboard());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.deleteButton),  isDisplayed()));
        button6.perform(click());
    }

}
