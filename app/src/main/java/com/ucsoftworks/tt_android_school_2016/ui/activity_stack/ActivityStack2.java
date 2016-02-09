package com.ucsoftworks.tt_android_school_2016.ui.activity_stack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ucsoftworks.tt_android_school_2016.R;
import com.ucsoftworks.tt_android_school_2016.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityStack2 extends BaseActivity {

    @Bind(R.id.open3)
    Button open1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_stack2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.open3)
    public void onClick() {

        int flag = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if ((flag & 1) == 1) {

        }
        if (((flag >> 1) & 1) == 1) {

        }

        Intent intent = new Intent(this, ActivityStack1.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
