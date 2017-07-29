package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.android.androidjokesdisplay.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeResultListener {
    private ProgressBar mLoadingSpinner;
    private JokeResultListener listener;
    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingSpinner = (ProgressBar) root.findViewById(R.id.progressBar);

        final Button buttonTellJoke = (Button) root.findViewById(R.id.button_tell_joke);
        buttonTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });

        return root;
    }

    public void onJokeReceived(String joke) {
        mLoadingSpinner.setVisibility(View.INVISIBLE);
            launchJokeDisplayActivity(joke);
        }

    private void getJoke() {
        mLoadingSpinner.setVisibility(View.VISIBLE);
        new EndpointAsyncTask(this).execute();
    }

    private void launchJokeDisplayActivity(String joke) {
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_KEY_EXTRA, joke);
        startActivity(intent);
    }
}

