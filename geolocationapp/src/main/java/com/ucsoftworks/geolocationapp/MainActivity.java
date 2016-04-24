package com.ucsoftworks.geolocationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this);
        setContentView(R.layout.activity_main);
    }
}
