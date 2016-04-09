package com.thumbtack2016.chat.ui.main_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.app.App;
import com.thumbtack2016.chat.network.AuthApi;
import com.thumbtack2016.chat.ui.dialogs.AuthDialog;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    AuthApi authApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getApp(this).getAppComponent().inject(this);
        
    }
}
