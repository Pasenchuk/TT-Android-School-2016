package com.ucsoftworks.lists.ui.normal_list;

import android.content.Context;
import android.util.Log;
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
            Log.d("ADAPTER", "CREATE VIEW");
            convertView = LayoutInflater.from(getContext())
                    .inflate(getItemViewType(position) == 0 ?
                                    R.layout.list_item_weapon : R.layout.list_item_weapon_odd
                            , parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        final Weapon item = getItem(position);
        viewHolder.weaponDescription.setText(String.format("%s %s", item.getWeaponDescription().toString(), item.getWeaponKind().toString()));
        switch (item.getWeaponKind()) {
            case FIST:
                viewHolder.weaponIcon.setImageResource(R.drawable.fist);
                break;
            case BULLET:
                viewHolder.weaponIcon.setImageResource(R.drawable.bullet);
                break;
            case BOMB:
                viewHolder.weaponIcon.setImageResource(R.drawable.bomb);
                break;
            case MISSILE:
                viewHolder.weaponIcon.setImageResource(R.drawable.missile);
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
