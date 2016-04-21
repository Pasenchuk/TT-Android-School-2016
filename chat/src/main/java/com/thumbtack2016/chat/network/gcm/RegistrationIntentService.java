package com.thumbtack2016.chat.network.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.app.App;
import com.thumbtack2016.chat.app.AppPreferences;
import com.thumbtack2016.chat.network.ChatApi;

import java.io.IOException;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wildf on 15.10.2015
 */
public class RegistrationIntentService extends IntentService {

    @Inject
    AppPreferences appPreferences;

    @Inject
    ChatApi chatApi;

    public RegistrationIntentService() {
        super("RegistrationIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ((App) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final String name = android.os.Build.MODEL;
        String registrationId;
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            registrationId = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            appPreferences
                    .setGcmId(registrationId)
                    .setGcmRegistered(false);

            if (appPreferences.isLoggedIn())
                chatApi
                        .gcmRegister(Build.MODEL, null, appPreferences.getGcmId())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                appPreferences.setGcmRegistered(true);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
            Log.d("GCM", registrationId);
        } catch (IOException e) {
            appPreferences
                    .setGcmId(null)
                    .setGcmRegistered(false);
        }

    }

}
