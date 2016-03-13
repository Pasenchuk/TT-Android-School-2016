package com.ucsoftworks.lists.ui.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.WeaponsViewHolder;
import com.ucsoftworks.lists.utils.WeaponListUtils;

import java.util.List;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class RecyclerWeaponsAdapter extends RecyclerView.Adapter<WeaponsViewHolder> {

    private Context context;
    private List<Weapon> weapons;

    public RecyclerWeaponsAdapter(Context context, List<Weapon> weapons) {
        this.context = context;
        this.weapons = weapons;
    }

    @Override
    public WeaponsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RECYCLER", "onCreateViewHolder");
        final View view = LayoutInflater.from(context)
                .inflate(viewType == 0 ?
                                R.layout.list_item_weapon : R.layout.list_item_weapon_odd
                        , parent, false);

        return new WeaponsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeaponsViewHolder holder, int position) {
        Log.d("RECYCLER", "onBindViewHolder");

        WeaponListUtils.fillWeaponListItem(holder, weapons.get(position));

    }

    @Override
    public int getItemCount() {
        return weapons.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position & 1;
    }
}
