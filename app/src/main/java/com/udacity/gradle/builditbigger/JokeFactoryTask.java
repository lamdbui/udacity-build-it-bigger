package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.lamdbui.jokefactory.backend.jokeFactoryApi.JokeFactoryApi;

import java.io.IOException;

/**
 * Created by lamdbui on 3/3/17.
 */

public class JokeFactoryTask extends AsyncTask<Pair<Context, String>, Void, String> {


    private static JokeFactoryApi sJokeFactoryApi;
    private Context mContext;
    private JokeFactoryTaskListener mCallback;
    private String mArgString;  // could be used later for arguments
    private boolean mFetchSuccess;

    // Caller must implement this, if they need additional notifications!
    public interface JokeFactoryTaskListener {

        public void finishedFetching(boolean success, String result);
    }

    public JokeFactoryTask() {
    }

    public JokeFactoryTask(JokeFactoryTaskListener callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {

        // only do this once
        if(sJokeFactoryApi == null) {

            // for local configuration
            JokeFactoryApi.Builder builder = new JokeFactoryApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            // for Google Cloud Engine configuration
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                    .setRootUrl("https://udacity-builditbigger-160500.appspot.com/_ah/api/")
//                    .setApplicationName("udacity-builditbigger-160500");

            sJokeFactoryApi = builder.build();
        }

        mContext = params[0].first;
        mArgString = params[0].second;

        try {
            mFetchSuccess = true;
            return sJokeFactoryApi.getJoke().execute().getData();
        }
        catch(IOException e) {
            mFetchSuccess = false;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mCallback != null)
            mCallback.finishedFetching(mFetchSuccess, result);
    }
}
