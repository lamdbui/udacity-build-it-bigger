package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by lamdbui on 3/5/17.
 */

@RunWith(AndroidJUnit4.class)
public class AdViewTest {

    // This actually ensures the Activity is launched
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void verifyBannerAdShowsInFreeFlavor() {
        Espresso.onView(withId(R.id.adView)).check(matches(isDisplayed()));
    }
}
