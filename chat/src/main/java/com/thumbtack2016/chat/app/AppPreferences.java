package com.thumbtack2016.chat.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pasencukviktor on 10/04/16
 */
public class AppPreferences {
    public static final String APP_PREFERENCES = "APP_PREFERENCES";

    public static final String
            GCM_ID_KEY = "GCM_ID_KEY",
            GCM_REGISTERED_KEY = "GCM_REGISTERED_KEY";

    private SharedPreferences sharedPreferences;

    public AppPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public AppPreferences setGcmId(String gcmId) {
        sharedPreferences
                .edit()
                .putString(GCM_ID_KEY, gcmId)
                .apply();
        return this;
    }

    public String getGcmId() {
        return sharedPreferences.getString(GCM_ID_KEY, null);
    }

    public AppPreferences setGcmRegistered(boolean gcmRegistered) {
        sharedPreferences
                .edit()
                .putBoolean(GCM_REGISTERED_KEY, gcmRegistered)
                .apply();
        return this;
    }

    public boolean getGcmRegistered() {
        return sharedPreferences.getBoolean(GCM_REGISTERED_KEY, false);
    }
}
