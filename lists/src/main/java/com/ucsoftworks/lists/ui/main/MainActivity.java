package com.ucsoftworks.lists.ui.main;

import android.os.Bundle;

import com.squareup.otto.Subscribe;
import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.events.ExpandableListClickEvent;
import com.ucsoftworks.lists.events.NormalListClickEvent;
import com.ucsoftworks.lists.events.RecyclerViewClickEvent;
import com.ucsoftworks.lists.ui.base.BaseActivity;
import com.ucsoftworks.lists.ui.expandable_list.ExpandableListFragment;
import com.ucsoftworks.lists.ui.normal_list.NormalListFragment;
import com.ucsoftworks.lists.ui.recycler_view.RecyclerListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            addFragment(new MainFragment());

    }

    @Subscribe
    public void onNormalListClickEvent(NormalListClickEvent event) {
        replaceFragment(new NormalListFragment());
    }

    @Subscribe
    public void onExpandableListClickEvent(ExpandableListClickEvent event) {
        replaceFragment(new ExpandableListFragment());
    }

    @Subscribe
    public void onRecyclerViewClickEvent(RecyclerViewClickEvent event) {
        replaceFragment(new RecyclerListFragment());
    }
}
