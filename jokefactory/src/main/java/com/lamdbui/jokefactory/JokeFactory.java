package com.lamdbui.jokefactory;

import java.util.ArrayList;
import java.util.Random;

public class JokeFactory {

    private ArrayList<String> mJokes;

    private Random mRandomizer;

    public JokeFactory() {
        mJokes = new ArrayList<>();

        mRandomizer = new Random();

        initJokeList();
    }

    private void initJokeList() {
        if(mJokes == null)
            mJokes = new ArrayList<>();

        // add some basic jokes
        mJokes.add("What did the mountain climber name his son? Cliff.");
        mJokes.add("Did you hear that the police have a warrant out on a midget psychic ripping people off? It reads " +
                "\"Small medium at large.\"");
        mJokes.add("Whenever the cashier at the grocery store asks my dad if he would like the milk in a bag he replies, No, just leave it in the carton!");
        mJokes.add("I went to a book store and asked the saleswoman where the Self Help section was, she said if " +
                "she told me it would defeat the purpose.");
        mJokes.add("Milk is also the fastest liquid on earth – it's pasteurized before you even see it");
        mJokes.add("What’s Forrest Gump’s password? 1forrest1");
        mJokes.add("What is a pirate's favorite color? ARRANGE!");
        mJokes.add("A red and a blue ship have just collided in the Caribbean. Apparently the survivors are marooned.");
        mJokes.add("How do you make holy water? You boil the hell out of it.");
        mJokes.add("I’m reading a book on the history of glue – can’t put it down.");
        mJokes.add("What do you call a cow with no legs? Ground beef.");
    }

    public String getJoke() {
        return mJokes.get(mRandomizer.nextInt(mJokes.size()));
    }
}
