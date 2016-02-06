package com.ucsoftworks.tt_android_school_2016.ui.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ucsoftworks.tt_android_school_2016.R;

/**
 * Created by pasencukviktor on 06/02/16.
 */
public abstract class BaseActivity extends AppCompatActivity {


    public void replaceFragment(Fragment fragment) {

        replaceFragment(fragment, true, null);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {

        replaceFragment(fragment, addToBackStack, null);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, @Nullable String key) {

        FragmentTransaction replaceTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment);

        if (addToBackStack)
            replaceTransaction
                    .addToBackStack(key);

        replaceTransaction
                .commit();
    }
}
