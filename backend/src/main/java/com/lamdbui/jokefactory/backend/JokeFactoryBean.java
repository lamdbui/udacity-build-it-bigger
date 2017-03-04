package com.lamdbui.jokefactory.backend;

import com.lamdbui.jokefactory.JokeFactory;

/**
 * The object model for the data we are sending through endpoints
 */
public class JokeFactoryBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}