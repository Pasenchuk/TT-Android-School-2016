package com.ucsoftworks.networkapp.ui.main_screen;

import android.os.Bundle;

import com.ucsoftworks.networkapp.R;
import com.ucsoftworks.networkapp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(new MainFragment());
        }
    }
}
