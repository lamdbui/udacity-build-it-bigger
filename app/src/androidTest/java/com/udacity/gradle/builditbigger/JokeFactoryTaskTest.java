package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.assertNotNull;

/**
 * Created by lamdbui on 3/4/17.
 */
@RunWith(AndroidJUnit4.class)
public class JokeFactoryTaskTest {

    @Test
    public void returnsValidString() {

        String result = null;

        JokeFactoryTask jokeFactoryTask = new JokeFactoryTask();
        jokeFactoryTask.execute(new Pair<Context, String>(getContext(), ""));

        try {
            result = jokeFactoryTask.get();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);
    }
}