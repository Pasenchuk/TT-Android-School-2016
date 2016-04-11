package com.thumbtack2016.chat.ui.main_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.app.App;
import com.thumbtack2016.chat.network.AuthApi;
import com.thumbtack2016.chat.network.models.Auth;
import com.thumbtack2016.chat.network.models.Token;
import com.thumbtack2016.chat.ui.dialogs.AuthDialog;
import com.thumbtack2016.chat.ui.dialogs.MessageBox;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    AuthApi authApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getApp(this).getAppComponent().inject(this);

        new AuthDialog(this)
                .show(getString(R.string.please_auth), "", "")
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Auth, Observable<Token>>() {
                    @Override
                    public Observable<Token> call(Auth auth) {
                        return authApi.getToken(auth);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Token>() {
                    @Override
                    public void call(Token token) {
                        MessageBox.show(token.getToken(), MainActivity.this);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        MessageBox.show(getString(R.string.network_error), MainActivity.this);
                    }
                });
    }
}
