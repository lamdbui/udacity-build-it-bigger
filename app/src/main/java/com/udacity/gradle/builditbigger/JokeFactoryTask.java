package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.lamdbui.jokefactory.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by lamdbui on 3/3/17.
 */

public class JokeFactoryTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi sMyApiService;
    private Context mContext;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

        // only do this once
        if(sMyApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                        new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                        request.setDisableGZipContent(true);
                    }
                });

            sMyApiService = builder.build();
        }

        mContext = params[0].first;
        String name = params[0].second;

        try {
            return sMyApiService.sayHi(name).execute().getData();
        }
        catch(IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
    }
}
