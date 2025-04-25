package com.example.bmicalculator;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import com.example.bmicalculator.MainActivity;

public class MainActivityEspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavigateToBMICalculator() {
        Espresso.onView(ViewMatchers.withId(R.id.btnBMI))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.weightInput))
                .check(matches(ViewMatchers.isDisplayed()));
    }


}
