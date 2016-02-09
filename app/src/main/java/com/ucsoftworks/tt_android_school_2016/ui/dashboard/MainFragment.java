package com.ucsoftworks.tt_android_school_2016.ui.dashboard;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ucsoftworks.tt_android_school_2016.R;
import com.ucsoftworks.tt_android_school_2016.events.OpenFragmentStackExampleEvent;
import com.ucsoftworks.tt_android_school_2016.events.OpenNewActivityEvent;
import com.ucsoftworks.tt_android_school_2016.ui.activity_stack.ActivityStack1;
import com.ucsoftworks.tt_android_school_2016.ui.base.BaseFragment;
import com.ucsoftworks.tt_android_school_2016.ui.fragment_stack.ActivityFragmentStack;
import com.ucsoftworks.tt_android_school_2016.ui.new_activity.ActivityNew;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    @Bind(R.id.info_label)
    TextView label;

    TextView noButterKnifeLabel;

    int counter = 0;
    @Bind(R.id.no_butter_knife_view)
    TextView noButterKnifeView;
    @Bind(R.id.push_me_button)
    Button pushMeButton;
    @Bind(R.id.press_me_button)
    Button pressMeButton;
    @Bind(R.id.press_no_butter_knife_button)
    Button pressNoButterKnifeButton;
    @Bind(R.id.new_activity)
    Button newActivity;
    @Bind(R.id.activity_stack)
    Button activityStack;

    private MainFragmentCallbacks callbacks;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentCallbacks) {
            callbacks = (MainFragmentCallbacks) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        noButterKnifeLabel = (TextView) (view.findViewById(R.id.no_butter_knife_view));

        view
                .findViewById(R.id.press_no_butter_knife_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        noButterKnifeLabel.setText(getString(R.string.no_butter_knife_pressed));
                    }
                });

        ButterKnife.bind(this, view);
        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.activity_stack)
    public void onClickActivityStack() {
//        Так не делать можно, но не нужно:

        getActivity().startActivity(new Intent(getActivity(), ActivityStack1.class));

//        Причина: это не является ответственностью фрагмента
//        Луче использовать шину:
//        getBus().post(new OpenActivityStackExampleEvent());


//        Или коллбэк:
//        if (callbacks != null)
//            callbacks.onActivityStackExampleButtonPressed();

//        Коллбэки надо описывать ручками и потом имплементить их активити-хостом
//        Коллбэки очень тяжело использовать, если есть ChildFragmentManager
    }

    @OnClick(R.id.new_activity)
    public void onClickNew() {
//        Шина:
        getBus().post(new OpenNewActivityEvent());

//        Прямой вызов:
//        getActivity().startActivity(new Intent(getActivity(), ActivityNew.class));


//        Коллбэк:
//        if (callbacks != null)
//            callbacks.onNewActivityButtonPressed();
    }

    @OnClick(R.id.fragment_precise_stack)
    public void onClickFragmentStack() {
//        Шина:
        getBus().post(new OpenFragmentStackExampleEvent());


//        Прямой вызов:
//        getActivity().startActivity(new Intent(getActivity(), ActivityFragmentStack.class));

//        Коллбэк:
//        if (callbacks != null)
//            callbacks.onFragmentStackExampleButtonPressed();

    }

    public interface MainFragmentCallbacks {
        void onNewActivityButtonPressed();

        void onActivityStackExampleButtonPressed();

        void onFragmentStackExampleButtonPressed();
    }
}
