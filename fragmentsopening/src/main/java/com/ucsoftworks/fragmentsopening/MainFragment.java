package com.ucsoftworks.fragmentsopening;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment {

    public static final String
            MESSAGE_KEY = "MESSAGE_KEY",
            VALUE_TEXT_KEY = "VALUE_TEXT_KEY",
            VALUE_KEY = "VALUE_KEY";
    public static final int REQUEST_CODE = 10500;

    @Bind(R.id.message)
    TextView message;
    @Bind(R.id.value_text)
    TextView valueText;
    @Bind(R.id.enter_value)
    EditText enterValue;

    String messageString;

    int value;

    public static MainFragment getInstance(String message) {
        final MainFragment mainFragment = new MainFragment();
        final Bundle bundle = new Bundle();

        bundle.putString(MESSAGE_KEY, message);

        mainFragment.setArguments(bundle);
        return mainFragment;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initFromBundle(savedInstanceState != null ?
                savedInstanceState : getArguments());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(MESSAGE_KEY, message.getText().toString());
        outState.putString(VALUE_TEXT_KEY, enterValue.getText().toString());
        outState.putInt(VALUE_KEY, value);
    }

    private void initFromBundle(Bundle bundle) {
        if (bundle.containsKey(MESSAGE_KEY))
            message.setText(bundle.getString(MESSAGE_KEY));
        if (bundle.containsKey(VALUE_TEXT_KEY))
            enterValue.setText(bundle.getString(VALUE_TEXT_KEY));
        if (bundle.containsKey(VALUE_KEY)) {
            value = bundle.getInt(VALUE_KEY);
            valueText.setText(String.valueOf(value));
        }
    }

    @OnClick(R.id.set_value)
    public void onSetValueClick() {
        try {
            value = Integer.valueOf(enterValue.getText().toString());
            valueText.setText(String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), R.string.not_valid_number, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.open_activity)
    public void onOpenActivityClick() {
        startActivityForResult(new Intent(getActivity(), Activity2.class), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
