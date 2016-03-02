package com.ucsoftworks.fragmentsopening;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

import icepick.State;

public class MainActivity extends BaseActivity {

    public static final String VALUE_KEY = "VALUE_KEY";

    @State
    double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            value = new Random().nextDouble();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MainFragment.getInstance("asdf"))
                    .commit();
        }


        Log.d(VALUE_KEY, String.valueOf(value));

//        PlayMarketUtils.openPlayMarketDevPage(MainActivity.this, "5700313618786177705");
    }

}
