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
            TOKEN_KEY = "TOKEN_KEY",
            LOGGED_IN_KEY = "LOGGED_IN_KEY",
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

    public AppPreferences setLoggedIn(boolean loggedIn) {
        sharedPreferences
                .edit()
                .putBoolean(LOGGED_IN_KEY, loggedIn)
                .apply();
        return this;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(LOGGED_IN_KEY, false);
    }

    public AppPreferences setGcmRegistered(boolean gcmRegistered) {
        sharedPreferences
                .edit()
                .putBoolean(GCM_REGISTERED_KEY, gcmRegistered)
                .apply();
        return this;
    }

    public boolean isGcmRegistered() {
        return sharedPreferences.getBoolean(GCM_REGISTERED_KEY, false);
    }


    public AppPreferences setToken(String token) {
        sharedPreferences
                .edit()
                .putString(TOKEN_KEY, token)
                .apply();
        return this;
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }
}
