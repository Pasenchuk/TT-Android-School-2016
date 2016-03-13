package com.ucsoftworks.lists.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucsoftworks.lists.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class WeaponsViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.weapon_icon)
    ImageView weaponIcon;
    @Bind(R.id.weapon_description)
    TextView weaponDescription;

    public WeaponsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public ImageView getWeaponIcon() {
        return weaponIcon;
    }

    public TextView getWeaponDescription() {
        return weaponDescription;
    }
}
