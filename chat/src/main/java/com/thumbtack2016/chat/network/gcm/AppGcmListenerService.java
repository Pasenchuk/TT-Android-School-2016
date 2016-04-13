package com.thumbtack2016.chat.network.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.ui.main_screen.MainActivity;

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
        Log.d("GCM data", data.toString());


        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        intent.putExtra(MainActivity.KEY_IS_FROM_NOTIFICATION, true);

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

// TODO: change text: show all messages
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.incoming_message))
                .setContentText(data.toString())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());

    }

}
