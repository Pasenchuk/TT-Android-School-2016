package com.ucsoftworks.lists.ui.recycler_view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.events.WeaponClickEvent;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerListFragment extends BaseFragment {


    public static final int CAPACITY = 100;

    @Bind(R.id.weapons)
    RecyclerView weaponsListView;
    private ArrayList<Weapon> weaponArrayList;
    private RecyclerWeaponsAdapter weaponsAdapter;

    public RecyclerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_list, container, false);
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
        weaponArrayList = new ArrayList<>(CAPACITY * 2);
        for (int i = 0; i < CAPACITY; i++)
            weaponArrayList.add(new Weapon());

        weaponsAdapter = new RecyclerWeaponsAdapter(getActivity(), weaponArrayList, getBus());
        weaponsListView.setAdapter(weaponsAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        weaponsListView.setLayoutManager(linearLayoutManager);
    }



// With butterknife
//
//    @OnItemClick(R.id.weapons)
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        Toast.makeText(getActivity(), weaponArrayList.get(position).getWeaponDescriptionString(), Toast.LENGTH_SHORT).show();
//
//    }

    @OnClick(R.id.add_item)
    public void onAddItemClick() {
        weaponArrayList.add(1, new Weapon());
        weaponsAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.remove_item)
    public void onRemoveItemClick() {
        weaponArrayList.remove(1);
        weaponsAdapter.notifyDataSetChanged();
    }


    @Subscribe
    public void onWeaponClickEvent(WeaponClickEvent event) {
        Toast.makeText(getActivity(), event.weapon.getWeaponDescriptionString(), Toast.LENGTH_SHORT).show();
    }
}
