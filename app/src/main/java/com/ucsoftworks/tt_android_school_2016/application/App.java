package com.ucsoftworks.tt_android_school_2016.application;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by pasencukviktor on 06/02/16.
 */
public class App extends Application {

    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new Bus();
    }



    public Bus getBus() {
        return bus;
    }
}
