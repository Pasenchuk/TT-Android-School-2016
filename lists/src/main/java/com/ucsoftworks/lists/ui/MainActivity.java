package com.ucsoftworks.lists.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
