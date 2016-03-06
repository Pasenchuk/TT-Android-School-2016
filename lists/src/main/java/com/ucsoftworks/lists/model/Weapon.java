package com.ucsoftworks.lists.model;

import java.util.Random;

/**
 * Created by pasencukviktor on 06/03/16
 */
public class Weapon {

    private WeaponKind weaponKind;
    private WeaponDescription weaponDescription;

    private static final Random random = new Random();

    public Weapon() {
        final WeaponKind[] weaponKinds = WeaponKind.values();
        final WeaponDescription[] weaponDescriptions = WeaponDescription.values();
        this.weaponKind = weaponKinds[random.nextInt(weaponKinds.length)];
        this.weaponDescription = weaponDescriptions[random.nextInt(weaponDescriptions.length)];
    }

    public WeaponKind getWeaponKind() {
        return weaponKind;
    }

    public WeaponDescription getWeaponDescription() {
        return weaponDescription;
    }
}
