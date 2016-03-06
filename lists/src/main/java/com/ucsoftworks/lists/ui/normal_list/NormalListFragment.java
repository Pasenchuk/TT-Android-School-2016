package com.ucsoftworks.lists.ui.normal_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalListFragment extends BaseFragment {


    public static final int CAPACITY = 100;

    @Bind(R.id.weapons)
    AbsListView weaponsListView;

    public NormalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_normal_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Weapon> weapons = new ArrayList<>(CAPACITY);
        for (int i = 0; i < 100; i++)
            weapons.add(new Weapon());
        weaponsListView.setAdapter(new WeaponsAdapter(getActivity(), 0, weapons));
    }
}
