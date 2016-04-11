package com.thumbtack2016.chat.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pasencukviktor on 10/04/16
 */
public class Preferences {
    public static final String APP_PREFERENCES = "APP_PREFERENCES";

    public static final String
            GCM_ID_KEY = "GCM_ID_KEY",
            GCM_REGISTERED_KEY = "GCM_REGISTERED_KEY";

    private SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public Preferences setGcmId(String gcmId) {
        sharedPreferences
                .edit()
                .putString(GCM_ID_KEY, gcmId)
                .apply();
        return this;
    }

    public Preferences setGcmRegistered(boolean gcmRegistered) {
        sharedPreferences
                .edit()
                .putBoolean(GCM_REGISTERED_KEY, gcmRegistered)
                .apply();
        return this;
    }
}
