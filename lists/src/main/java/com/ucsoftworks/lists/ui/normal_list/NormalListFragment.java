
package com.ucsoftworks.lists.ui.normal_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalListFragment extends BaseFragment {


    public NormalListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_normal_list, container, false);
    }

}
