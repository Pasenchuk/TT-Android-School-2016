package com.ucsoftworks.fragmentsopening;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import icepick.Icepick;

/**
 * Created by pasencukviktor on 28/02/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            Icepick.restoreInstanceState(this, savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }
}
