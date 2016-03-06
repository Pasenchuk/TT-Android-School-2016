package com.ucsoftworks.lists.ui.normal_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;

import java.util.List;

/**
 * Created by pasencukviktor on 06/03/16
 */
public class WeaponsAdapter extends ArrayAdapter<Weapon> {
    public WeaponsAdapter(Context context, int resource, List<Weapon> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_weapon, parent, false);
        }
        return convertView;
    }
}
