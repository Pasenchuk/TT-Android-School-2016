package com.ucsoftworks.tt_android_school_2016.ui.fragment_stack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ucsoftworks.tt_android_school_2016.R;
import com.ucsoftworks.tt_android_school_2016.ui.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.open1)
    public void onReturnToF1Click() {
        // TODO: 10/02/16 Сменить прямое обращение на шину!
        getBaseActivity().returnToBackStack(ActivityFragmentStack.FRAGMENT2, true);
    }

    @OnClick(R.id.open2)
    public void onReturnToF2Click() {
        // TODO: 10/02/16 Сменить прямое обращение на шину!
        getBaseActivity().returnToBackStack(ActivityFragmentStack.FRAGMENT2, false);
    }
}
