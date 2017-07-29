package com.example.android.androidjokesdisplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Rebecca on 7/24/2017.
 */

public class JokeDisplayFragment extends Fragment {

    public JokeDisplayFragment() {
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_joke_display, container, false);
        String joke = getActivity().getIntent().getStringExtra(JokeDisplayActivity.JOKE_KEY_EXTRA);

        TextView jokeTextView = (TextView) root.findViewById(R.id.joke_display);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

        return root;
    }
}
