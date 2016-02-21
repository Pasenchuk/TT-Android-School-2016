package com.ucsoftworks.intentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {


    public static final String
            CODE_MESSAGE_STRING = "CODE_MESSAGE_STRING";
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.input_field)
    EditText inputField;
    @Bind(R.id.form_container)
    LinearLayout formContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        final Intent intent = getIntent();


        if (intent.hasExtra(CODE_MESSAGE_STRING))
            title.setText(intent.getStringExtra(CODE_MESSAGE_STRING));
        else
            title.setText(R.string.no_extra);
    }

    @OnClick(R.id.press_button)
    public void onClick() {
        final Intent intent = new Intent();
        intent.putExtra(CODE_MESSAGE_STRING, inputField.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
