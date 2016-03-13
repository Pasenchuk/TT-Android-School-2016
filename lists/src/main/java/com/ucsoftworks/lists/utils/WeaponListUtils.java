package com.ucsoftworks.lists.utils;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.WeaponsViewHolder;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class WeaponListUtils {
    public static void fillWeaponListItem(WeaponsViewHolder viewHolder, Weapon item) {
        viewHolder.getWeaponDescription().setText(item.getWeaponDescriptionString());
        switch (item.getWeaponKind()) {
            case FIST:
                viewHolder.getWeaponIcon().setImageResource(R.drawable.fist);
                break;
            case BULLET:
                viewHolder.getWeaponIcon().setImageResource(R.drawable.bullet);
                break;
            case BOMB:
                viewHolder.getWeaponIcon().setImageResource(R.drawable.bomb);
                break;
            case MISSILE:
                viewHolder.getWeaponIcon().setImageResource(R.drawable.missile);
                break;
        }
    }
}
