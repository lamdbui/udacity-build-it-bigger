package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.builditbigger.JokeFactoryTask;
import com.udacity.gradle.builditbigger.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.joke_button)
    Button mJokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JokeFactoryTask().execute(new Pair<Context, String>(getActivity(), ""));
            }
        });

        return root;
    }
}
