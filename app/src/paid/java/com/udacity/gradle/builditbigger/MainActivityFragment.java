package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.JokeFactoryTask;
import com.udacity.gradle.builditbigger.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.SystemClock.sleep;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment
    implements JokeFactoryTask.JokeFactoryTaskListener {

    @BindView(R.id.joke_button)
    Button mJokeButton;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.joke_text_view)
    TextView mJokeTextView;

    public MainActivityFragment() {
    }

    @Override
    public void finishedFetching(String result) {
        mProgressBar.setVisibility(View.GONE);

        if(result != null) {
            mJokeTextView.setText(result);
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
