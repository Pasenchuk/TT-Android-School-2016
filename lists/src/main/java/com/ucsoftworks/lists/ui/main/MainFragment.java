package com.ucsoftworks.lists.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.events.ExpandableListClickEvent;
import com.ucsoftworks.lists.events.NormalListClickEvent;
import com.ucsoftworks.lists.events.RecyclerViewClickEvent;
import com.ucsoftworks.lists.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.normal_list, R.id.expandable_list, R.id.recycler_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.normal_list:
                getBus().post(new NormalListClickEvent());
                break;
            case R.id.expandable_list:
                getBus().post(new ExpandableListClickEvent());
                break;
            case R.id.recycler_view:
                getBus().post(new RecyclerViewClickEvent());
                break;
        }
    }
}
