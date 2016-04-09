package com.thumbtack2016.chat.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.thumbtack2016.chat.R;
import com.thumbtack2016.chat.app.App;
import com.thumbtack2016.chat.app.Preferences;
import com.thumbtack2016.chat.network.models.Auth;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by Pasenchuk Victor on 10.06.15
 */
public class AuthDialog {


    private final Activity activity;
    private ViewHolder viewHolder;
    private AlertDialog alertDialog;

    @Inject
    Preferences preferences;

    public AuthDialog(Activity activity) {
        this.activity = activity;
        App.getApp(activity).getAppComponent().inject(this);
    }


    public Observable<Auth> show(final String title, final String login, final String password) {
        return Observable.create(new Observable.OnSubscribe<Auth>() {
            @Override
            public void call(final Subscriber<? super Auth> subscriber) {

                final View view = activity.getLayoutInflater().inflate(R.layout.dialog_auth, null);
                viewHolder = new ViewHolder(view);

                if (!TextUtils.isEmpty(login))
                    viewHolder.login.setText(login);
                if (!TextUtils.isEmpty(password))
                    viewHolder.password.setText(login);

                alertDialog = new AlertDialog.Builder(activity)
                        .setView(view)
                        .setTitle(title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                subscriber.onNext(new Auth(
                                        viewHolder.login.getText().toString(),
                                        viewHolder.password.getText().toString()
                                ));
                                subscriber.onCompleted();
                            }
                        })
                        .setCancelable(false)
                        .create();

                // cleaning up
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        alertDialog.dismiss();
                    }
                }));

                alertDialog.show();

            }
        });

    }

    public void dismiss() {
        if (alertDialog != null)
            alertDialog.dismiss();
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'dialog_auth.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    public static class ViewHolder {
        @Bind(R.id.login)
        EditText login;
        @Bind(R.id.password)
        EditText password;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public EditText getLogin() {
            return login;
        }

        public EditText getPassword() {
            return password;
        }
    }

}
