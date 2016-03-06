package com.ucsoftworks.lists.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.squareup.otto.Bus;
import com.ucsoftworks.lists.application.App;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by pasencukviktor on 06/02/16.
 */
public abstract class BaseFragment extends Fragment {


    private Bus bus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("LifeCycle", this.getClass().getName() + " OnCreate");
        if (savedInstanceState != null)
            Icepick.restoreInstanceState(this, savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onStart() {
        super.onStart();
        bus = App.getApp(this).getBus();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Override
    public void onDestroy() {
        Log.d("LifeCycle", this.getClass().getName() + " onDestroy");
        super.onDestroy();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public Bus getBus() {
        return bus;
    }
}
