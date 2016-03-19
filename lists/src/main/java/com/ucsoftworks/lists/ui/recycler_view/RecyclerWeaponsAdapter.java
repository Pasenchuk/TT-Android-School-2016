package com.ucsoftworks.lists.ui.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Bus;
import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.events.WeaponClickEvent;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.WeaponsViewHolder;
import com.ucsoftworks.lists.utils.WeaponListUtils;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class RecyclerWeaponsAdapter extends RecyclerView.Adapter<RecyclerWeaponsAdapter.ClickableWeaponsViewHolder> {

    private Context context;
    private List<Weapon> weapons;
    private Bus bus;

    public RecyclerWeaponsAdapter(Context context, List<Weapon> weapons, Bus bus) {
        this.context = context;
        this.weapons = weapons;
        this.bus = bus;

        setHasStableIds(true);
    }

    @Override
    public ClickableWeaponsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RECYCLER", "onCreateViewHolder");
        final View view = LayoutInflater.from(context)
                .inflate(viewType == 0 ?
                                R.layout.recycler_item_weapon_even : R.layout.recycler_item_weapon_odd
                        , parent, false);

        return new ClickableWeaponsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClickableWeaponsViewHolder holder, int position) {
        Log.d("RECYCLER", "onBindViewHolder");

        WeaponListUtils.fillWeaponListItem(holder, weapons.get(position));
        holder.setPosition(position);

    }

    @Override
    public int getItemCount() {
        return weapons.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position & 1;
    }

    @Override
    public long getItemId(int position) {
        return weapons.get(position).getId();
    }


    class ClickableWeaponsViewHolder extends WeaponsViewHolder {
        private int position;

        public ClickableWeaponsViewHolder(View view) {
            super(view);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @OnClick(R.id.weapon_view)
        void onWeaponViewClick() {
            bus.post(new WeaponClickEvent(weapons.get(position)));
        }
    }

}
