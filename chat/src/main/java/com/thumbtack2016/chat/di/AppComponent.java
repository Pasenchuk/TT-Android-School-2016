package com.thumbtack2016.chat.di;

import com.thumbtack2016.chat.ui.base.BaseActivity;
import com.thumbtack2016.chat.ui.base.BaseFragment;
import com.thumbtack2016.chat.ui.dialogs.AuthDialog;
import com.thumbtack2016.chat.ui.main_screen.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pasencukviktor on 10/02/16
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    void inject(AuthDialog authDialog);

    void inject(MainActivity mainActivity);
}
