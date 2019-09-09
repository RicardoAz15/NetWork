package com.example.volley_montepio;


import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkIfItemDisplay() {

        for(int i = 0;i<6;i++){
            onView(withId(R.id.viewPagerContent)).
                    perform(RecyclerViewActions.scrollToPosition(i)).
                    check(matches(isDisplayed())).perform(click());

            activityRule.getActivity().
                    startActivity(
                            new Intent(activityRule.getActivity(),MainActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        }
    }
}