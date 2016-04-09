package com.thumbtack2016.chat.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.thumbtack2016.chat.R;


/**
 * Created with IntelliJ IDEA.
 * User: pasencukviktor
 * Date: 01.12.13
 * Time: 21:32
 */
public class MessageBox {

    public static void show(int resId, Context context) {
        show(context.getString(resId), context);
    }

    public static void show(CharSequence message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder
                .setMessage(message)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

}
