package com.thumbtack2016.chat.ui.main_screen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.app.App;
import com.thumbtack2016.chat.app.AppPreferences;
import com.thumbtack2016.chat.network.ChatApi;
import com.thumbtack2016.chat.network.gcm.RegistrationIntentService;
import com.thumbtack2016.chat.network.models.Auth;
import com.thumbtack2016.chat.network.models.Token;
import com.thumbtack2016.chat.ui.dialogs.AuthDialog;
import com.thumbtack2016.chat.ui.dialogs.MessageBox;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_IS_FROM_NOTIFICATION = "KEY_IS_FROM_NOTIFICATION";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    @Inject
    ChatApi chatApi;

    @Inject
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        App.getApp(this).getAppComponent().inject(this);


        if (!appPreferences.isLoggedIn())
            new AuthDialog(this)
                    .show(getString(R.string.please_auth), "", "")
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .flatMap(new Func1<Auth, Observable<Token>>() {
                        @Override
                        public Observable<Token> call(Auth auth) {
                            return chatApi.getToken(auth);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Token>() {
                        @Override
                        public void call(Token token) {
                            MessageBox.show(token.getToken(), MainActivity.this);

                            appPreferences
                                    .setToken(token.getToken())
                                    .setLoggedIn(true);

                            if (checkPlayServices()) {
                                if (!appPreferences.isGcmRegistered()) {
                                    registerGcm();
                                    Log.d("GCM ID", appPreferences.getGcmId());
                                } else {
                                    Log.d("GCM ID", "not created");

                                    startService(new Intent(MainActivity.this, RegistrationIntentService.class));
                                }
                            } else
                                Log.d("GCM ID", "no play services");

                        }
                    }, getOnError());
        else if (!appPreferences.isGcmRegistered() && checkPlayServices())
            registerGcm();
    }

    private void registerGcm() {
        chatApi
                .gcmRegister(Build.MODEL, null, appPreferences.getGcmId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        appPreferences.setGcmRegistered(true);
                    }
                }, getOnError());
    }

    @NonNull
    private Action1<Throwable> getOnError() {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                MessageBox.show(getString(R.string.network_error), MainActivity.this);
            }
        };
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                // Probably show alert there
                MessageBox.show(R.string.no_play_services, this);
            }
            return false;
        }
        return true;
    }
}
