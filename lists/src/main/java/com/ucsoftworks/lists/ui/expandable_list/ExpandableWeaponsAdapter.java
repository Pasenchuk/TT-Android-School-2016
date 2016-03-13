package com.ucsoftworks.lists.ui.expandable_list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.ucsoftworks.lists.model.GroupedWeapons;
import com.ucsoftworks.lists.model.Weapon;

import java.util.List;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class ExpandableWeaponsAdapter extends BaseExpandableListAdapter {

   private List<GroupedWeapons> groupedWeaponsList;
    private Context context;

    public ExpandableWeaponsAdapter(List<GroupedWeapons> groupedWeaponsList, Context context) {
        this.groupedWeaponsList = groupedWeaponsList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupedWeaponsList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return getGroup(i).getWeapons().size();
    }

    @Override
    public GroupedWeapons getGroup(int i) {
        return groupedWeaponsList.get(i);
    }

    @Override
    public Weapon getChild(int i, int i1) {
        return getGroup(i).getWeapons().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return getGroup(i).hashCode();
    }

    @Override
    public long getChildId(int i, int i1) {
        return getChild(i, i1).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
