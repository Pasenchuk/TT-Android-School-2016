package com.ucsoftworks.lists.ui;

import android.os.Bundle;

import com.squareup.otto.Subscribe;
import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.events.ExpandableListClickEvent;
import com.ucsoftworks.lists.events.NormalListClickEvent;
import com.ucsoftworks.lists.events.RecyclerViewClickEvent;
import com.ucsoftworks.lists.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Subscribe
    public void onNormalListClickEvent(NormalListClickEvent event) {

    }

    @Subscribe
    public void onExpandableListClickEvent(ExpandableListClickEvent event) {

    }

    @Subscribe
    public void onRecyclerViewClickEvent(RecyclerViewClickEvent event) {

    }
}
