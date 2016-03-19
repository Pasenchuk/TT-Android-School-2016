package com.ucsoftworks.lists.events;

import com.ucsoftworks.lists.model.Weapon;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class WeaponClickEvent {
    public final Weapon weapon;

    public WeaponClickEvent(Weapon weapon) {
        this.weapon = weapon;
    }
}
