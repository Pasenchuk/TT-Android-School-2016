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
}
