package com.ucsoftworks.intentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final int
            REQUEST_CODE_GET_STRING = 100_50;

    @Bind(R.id.input_field)
    EditText inputField;
    @Bind(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.press_button)
    public void onClick() {
        final Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra(SecondActivity.CODE_MESSAGE_STRING, inputField.getText().toString());

        startActivity(intent);
    }

    @OnClick(R.id.get_result_button)
    public void onGetResultClick() {
        final Intent intent = new Intent(this, SecondActivity.class);

        startActivityForResult(intent, REQUEST_CODE_GET_STRING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE_GET_STRING) {
            if (resultCode==RESULT_OK)
                title.setText(data.getStringExtra(SecondActivity.CODE_MESSAGE_STRING));
            else
                title.setText(R.string.no_result);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
