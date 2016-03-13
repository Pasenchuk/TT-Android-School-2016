package com.ucsoftworks.lists.ui.normal_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.WeaponsViewHolder;

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
        WeaponsViewHolder viewHolder;
        if (convertView == null) {
            Log.d("ADAPTER", "CREATE VIEW");
            convertView = LayoutInflater.from(getContext())
                    .inflate(getItemViewType(position) == 0 ?
                                    R.layout.list_item_weapon : R.layout.list_item_weapon_odd
                            , parent, false);

            viewHolder = new WeaponsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (WeaponsViewHolder) convertView.getTag();

        final Weapon item = getItem(position);
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
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }


}
