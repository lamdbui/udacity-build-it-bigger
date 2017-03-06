package app.com.lamdbui.android.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by lamdbui on 3/5/17.
 */
@RunWith(AndroidJUnit4.class)
public class JokeActivityTest {

    public static final String JOKE_SAMPLE = "Why did the eskimo eat a candle for lunch? Because he wanted a light lunch!";

    @Rule
    public ActivityTestRule<JokeActivity> mActivityTestRule =
            new ActivityTestRule<JokeActivity>(JokeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    // create a new Intent here and pass in a pre-defined joke for verification
                    Context targetContext = InstrumentationRegistry.getTargetContext();
                    Intent jokeIntent = JokeActivity.newIntent(targetContext, JOKE_SAMPLE);

                    return jokeIntent;
                };
            };

    @Test
    public void jokeTextShouldDisplayInTextView() {
        Espresso.onView(withId(R2.id.joke_text_view)).check(matches(withText(JOKE_SAMPLE)));
    }
}