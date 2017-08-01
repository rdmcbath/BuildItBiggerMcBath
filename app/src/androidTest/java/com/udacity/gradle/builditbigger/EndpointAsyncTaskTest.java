package com.udacity.gradle.builditbigger;

// Run an instance of your AsyncTask class
// Wait for the AsyncTask to finish
// Use the JUnit Assert methods to test the result

import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.view.View;

import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rebecca on 7/31/2017.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTaskTest extends AndroidTestCase implements JokeResultListener {

    EndpointAsyncTask fetchJoke;
    private JokeResultListener listener;
    CountDownLatch signal;
    String joke ="";

    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        fetchJoke = new EndpointAsyncTask(this);
    }

    public void getJoke() {
        new EndpointAsyncTask(this).execute();
    }

    @UiThreadTest
    public void testDownload() throws InterruptedException
    {
        fetchJoke.getJoke();
        signal.await(30, TimeUnit.SECONDS);

        assertTrue("Joke is returned", joke !=null);
    }

    @Override
    public void downloadCompleted(String j) {
        joke = j;
        signal.countDown();
    }
    
    @Override
    public void onJokeReceived(String joke) {

    }
}