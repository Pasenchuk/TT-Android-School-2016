package com.ucsoftworks.apptemplate.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pasencukviktor on 10/04/16
 */
public class Preferences {
    public static final String APP_PREFERENCES = "APP_PREFERENCES";
    private SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
}
