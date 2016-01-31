package com.ucsoftworks.tt_android_school_2016;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.info_label)
    TextView label;

    int counter = 0;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.push_me_button)
    public void onPushMeButtonClick() {
        counter++;
        label.setText(String.valueOf(counter));
    }

    @OnClick(R.id.press_me_button)
    public void onPressMeButtonClick() {
        Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_SHORT).show();
    }
}
