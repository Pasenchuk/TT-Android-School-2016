package com.ucsoftworks.lists.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucsoftworks.lists.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class WeaponsViewHolder {

    @Bind(R.id.weapon_icon)
    private ImageView weaponIcon;
    @Bind(R.id.weapon_description)
    private TextView weaponDescription;

    public WeaponsViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public ImageView getWeaponIcon() {
        return weaponIcon;
    }

    public TextView getWeaponDescription() {
        return weaponDescription;
    }
}
