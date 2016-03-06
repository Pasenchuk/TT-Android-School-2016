package com.ucsoftworks.lists.ui.normal_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pasencukviktor on 06/03/16
 */
public class WeaponsAdapter extends ArrayAdapter<Weapon> {
    public WeaponsAdapter(Context context, int resource, List<Weapon> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_weapon, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.weapon_icon)
        ImageView weaponIcon;
        @Bind(R.id.weapon_description)
        TextView weaponDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
