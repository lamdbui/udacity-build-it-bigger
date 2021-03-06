package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.com.lamdbui.android.jokedisplay.JokeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment
    implements JokeFactoryTask.JokeFactoryTaskListener {

    @BindView(R.id.joke_button)
    Button mJokeButton;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public void finishedFetching(boolean success, String result) {
        mProgressBar.setVisibility(View.GONE);

        if(success) {
            Intent jokeDisplayIntent = JokeActivity.newIntent(getActivity(), result);
            startActivity(jokeDisplayIntent);
        }
        // display a Toast with the error if the joke fetching didn't work
        else {
            String errorString = getString(R.string.error_fetching_joke);
            if(result != null) {
                errorString = errorString + " " + result;
            }
            Toast.makeText(getActivity(), errorString, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        mProgressBar.setVisibility(View.GONE);

        final JokeFactoryTask.JokeFactoryTaskListener callback = this;

        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                new JokeFactoryTask(callback).execute(new Pair<Context, String>(getActivity(), ""));
            }
        });

        return root;
    }
}
