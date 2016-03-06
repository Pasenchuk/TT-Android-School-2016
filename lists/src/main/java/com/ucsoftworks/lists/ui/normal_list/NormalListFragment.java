package com.ucsoftworks.lists.ui.normal_list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalListFragment extends BaseFragment {


    public static final int CAPACITY = 100;

    @Bind(R.id.weapons)
    AbsListView weaponsListView;
    private ArrayList<Weapon> weaponArrayList;
    private WeaponsAdapter weaponsAdapter;

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
        weaponArrayList = new ArrayList<>(CAPACITY * 2);
        for (int i = 0; i < CAPACITY; i++)
            weaponArrayList.add(new Weapon());

// If you want to sort
//        Collections.sort(weaponArrayList, new Comparator<Weapon>() {
//            @Override
//            public int compare(Weapon weapon, Weapon t1) {
//                return weapon.getWeaponDescriptionString().compareTo(t1.getWeaponDescriptionString());
//            }
//        });

        weaponsAdapter = new WeaponsAdapter(getActivity(), 0, weaponArrayList);
        weaponsListView.setAdapter(weaponsAdapter);

        weaponsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), weaponArrayList.get(position).getWeaponDescriptionString(), Toast.LENGTH_SHORT).show();

            }
        });
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
}
