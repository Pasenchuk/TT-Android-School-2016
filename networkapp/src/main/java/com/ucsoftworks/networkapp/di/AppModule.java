package com.ucsoftworks.networkapp.di;

import com.squareup.otto.Bus;
import com.ucsoftworks.networkapp.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pasencukviktor on 10/02/16
 */

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }

}
