package com.ucsoftworks.lists.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.squareup.otto.Bus;

/**
 * Created by pasencukviktor on 06/02/16.
 */
public class App extends Application {

    private Bus bus = new Bus();

    public static App getApp(Activity activity) {
        return (App) activity.getApplication();
    }

    public static App getApp(Fragment fragment) {
        final FragmentActivity activity = fragment.getActivity();
        if (activity != null)
            return (App) activity.getApplication();
        throw new IllegalStateException("Fragment must be attached to activity!");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Bus getBus() {
        return bus;
    }
}
