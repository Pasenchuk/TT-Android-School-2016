package com.ucsoftworks.lists.ui.expandable_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.GroupedWeapons;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.base.BaseFragment;
import com.ucsoftworks.lists.ui.normal_list.WeaponsAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandableListFragment extends BaseFragment {


    public static final int
            CAPACITY = 10,
            GROUP_COUNT = 100;

    @Bind(R.id.grouped_weapons)
    ExpandableListView groupedWeapons;
    private ArrayList<GroupedWeapons> weaponGroupsArrayList;
    private ExpandableWeaponsAdapter weaponsAdapter;

    public ExpandableListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expandable_list, container, false);
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


        weaponGroupsArrayList = new ArrayList<>(GROUP_COUNT);

        for (int i = 0; i < GROUP_COUNT; i++) {
            ArrayList<Weapon> weaponArrayList = new ArrayList<>(CAPACITY);
            for (int j = 0; j < CAPACITY; j++)
                weaponArrayList.add(new Weapon());

            weaponGroupsArrayList.add(new GroupedWeapons("Group " + i, weaponArrayList));
        }


        weaponsAdapter = new ExpandableWeaponsAdapter(weaponGroupsArrayList, getActivity());
        groupedWeapons.setAdapter(weaponsAdapter);
//
//        groupedWeapons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getActivity(), weaponArrayList.get(position).getWeaponDescriptionString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

// With butterknife
//
//    @OnItemClick(R.id.weapons)
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        Toast.makeText(getActivity(), weaponArrayList.get(position).getWeaponDescriptionString(), Toast.LENGTH_SHORT).show();
//
//    }
//
//    @OnClick(R.id.add_item)
//    public void onAddItemClick() {
//        weaponArrayList.add(1, new Weapon());
//        weaponsAdapter.notifyDataSetChanged();
//    }
//
//    @OnClick(R.id.remove_item)
//    public void onRemoveItemClick() {
//        weaponArrayList.remove(1);
//        weaponsAdapter.notifyDataSetChanged();
//    }
}
