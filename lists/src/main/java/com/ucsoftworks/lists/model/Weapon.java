package com.ucsoftworks.lists.model;

import java.util.Random;

/**
 * Created by pasencukviktor on 06/03/16
 */
public class Weapon {

    private WeaponKind weaponKind;
    private WeaponDescription weaponDescription;
    private long id;

    private static final Random random = new Random();

    public Weapon() {
        final WeaponKind[] weaponKinds = WeaponKind.values();
        final WeaponDescription[] weaponDescriptions = WeaponDescription.values();
        this.weaponKind = weaponKinds[random.nextInt(weaponKinds.length)];
        this.weaponDescription = weaponDescriptions[random.nextInt(weaponDescriptions.length)];
        id = random.nextLong();
    }

    public WeaponKind getWeaponKind() {
        return weaponKind;
    }

    public WeaponDescription getWeaponDescription() {
        return weaponDescription;
    }

    public long getId() {
        return id;
    }

    public String getWeaponDescriptionString() {
        return String.format("%s %s", getWeaponDescription().toString(), getWeaponKind().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weapon weapon = (Weapon) o;

        if (id != weapon.id) return false;
        if (weaponKind != weapon.weaponKind) return false;
        return weaponDescription == weapon.weaponDescription;

    }

    @Override
    public int hashCode() {
        int result = weaponKind != null ? weaponKind.hashCode() : 0;
        result = 31 * result + (weaponDescription != null ? weaponDescription.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
