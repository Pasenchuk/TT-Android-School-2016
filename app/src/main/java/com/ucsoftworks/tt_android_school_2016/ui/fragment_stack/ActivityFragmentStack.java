package com.ucsoftworks.tt_android_school_2016.ui.fragment_stack;

import android.os.Bundle;

import com.ucsoftworks.tt_android_school_2016.R;
import com.ucsoftworks.tt_android_school_2016.ui.base.BaseActivity;

public class ActivityFragmentStack extends BaseActivity {

    public static final String
            FRAGMENT2 = "FRAGMENT2",
            FRAGMENT3 = "FRAGMENT3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new Fragment1())
                    .commit();
    }
}
