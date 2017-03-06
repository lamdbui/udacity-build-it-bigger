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
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.JokeFactoryTask;
import com.udacity.gradle.builditbigger.R;

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

    @BindView(R.id.adView)
    AdView mAdView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.joke_text_view)
    TextView mJokeTextView;

    InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public void finishedFetching(String result) {
        mProgressBar.setVisibility(View.GONE);

//        if(result != null) {
//            mJokeTextView.setText(result);
//        }

        Intent jokeDisplayIntent = JokeActivity.newIntent(getActivity(), result);
        startActivity(jokeDisplayIntent);
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
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                new JokeFactoryTask(callback).execute(new Pair<Context, String>(getActivity(), ""));
            }
        });

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd.loadAd(adRequest);

        return root;
    }
}
