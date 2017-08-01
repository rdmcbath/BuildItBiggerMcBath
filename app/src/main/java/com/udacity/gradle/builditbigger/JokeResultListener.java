package com.udacity.gradle.builditbigger;

/**
 * Created by Rebecca on 7/24/2017.
 */

public interface JokeResultListener {

    void downloadCompleted(String j);

    void onJokeReceived(String joke);
}
