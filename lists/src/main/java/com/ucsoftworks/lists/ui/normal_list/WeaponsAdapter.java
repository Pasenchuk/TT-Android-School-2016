package com.ucsoftworks.lists.ui.normal_list;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ucsoftworks.lists.model.Weapon;

import java.util.List;

/**
 * Created by pasencukviktor on 06/03/16.
 */
public class WeaponsAdapter extends ArrayAdapter<Weapon> {
    public WeaponsAdapter(Context context, int resource, List<Weapon> objects) {
        super(context, resource, objects);
    }


}
