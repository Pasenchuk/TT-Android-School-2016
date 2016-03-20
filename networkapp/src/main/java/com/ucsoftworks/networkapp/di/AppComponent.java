package com.ucsoftworks.networkapp.di;

import com.ucsoftworks.networkapp.ui.base.BaseActivity;
import com.ucsoftworks.networkapp.ui.base.BaseFragment;

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

}
