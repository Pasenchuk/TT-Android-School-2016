package com.ucsoftworks.lists.model;

import java.util.List;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class GroupedWeapons {
    private String groupName;
    private List<Weapon> weapons;

    public GroupedWeapons(String groupName, List<Weapon> weapons) {
        this.groupName = groupName;
        this.weapons = weapons;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupedWeapons that = (GroupedWeapons) o;

        if (!groupName.equals(that.groupName)) return false;
        return weapons.equals(that.weapons);

    }

    @Override
    public int hashCode() {
        int result = groupName.hashCode();
        result = 31 * result + weapons.hashCode();
        return result;
    }
}
