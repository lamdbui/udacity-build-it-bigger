/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.lamdbui.jokefactory.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.lamdbui.jokefactory.JokeFactory;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeFactoryApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokefactory.lamdbui.com",
                ownerName = "backend.jokefactory.lamdbui.com",
                packagePath = ""
        )
)
public class JokeFactoryEndpoint {

    private JokeFactory mJokeFactory;

    public JokeFactoryEndpoint() {
        mJokeFactory = new JokeFactory();
    }

    /**
     * Returns a random joke
     */
    @ApiMethod(name = "getJoke")
    public JokeFactoryBean getJoke() {
        JokeFactoryBean response = new JokeFactoryBean();
        response.setData(mJokeFactory.getJoke());

        return response;
    }

}
