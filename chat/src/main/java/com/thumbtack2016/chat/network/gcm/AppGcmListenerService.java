package com.thumbtack2016.chat.network.gcm;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;

/**
 * Created by wildf on 15.10.2015
 */
public class AppGcmListenerService extends GcmListenerService {

    public static final String
            TYPE_CHAT_MESSAGE = "CHAT_MESSAGE",
            TYPE_CHAT_GROUP = "CHAT_GROUP";

    public static final String
            TYPE = "TYPE",
            DATA = "DATA";

    private static final int NOTIFICATION_ID = 0;


    public static void removeNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    @Override
    public void onCreate() {
        Log.d("GCM MESSAGE", "GCM Listener created");
        super.onCreate();
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.d("GCM from", from);
        Log.d("GCM data", new Gson().toJson(data));


    }

}
