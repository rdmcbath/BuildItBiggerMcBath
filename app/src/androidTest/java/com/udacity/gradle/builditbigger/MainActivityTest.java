package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by Rebecca on 8/1/2017.
 */
public class MainActivityTest {

    public class ApplicationTest extends ApplicationTestCase<Application> {
        public ApplicationTest() {
            super(Application.class);
        }
    }
}