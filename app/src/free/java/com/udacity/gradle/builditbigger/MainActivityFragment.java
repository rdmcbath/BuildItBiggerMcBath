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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeResultListener {
    private ProgressBar mLoadingSpinner;
    private JokeResultListener listener;
    private InterstitialAd mInterstitialAd;
    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingSpinner = (ProgressBar) root.findViewById(R.id.progressBar);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        final Button buttonTellJoke = (Button) root.findViewById(R.id.button_tell_joke);
        buttonTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoke();
                loadInterstitialAd();
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                launchJokeDisplayActivity(mJoke);
            }
        });

        return root;
    }

    public void onJokeReceived(String joke) {
        mLoadingSpinner.setVisibility(View.INVISIBLE);
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            launchJokeDisplayActivity(joke);
        }
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

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
}

