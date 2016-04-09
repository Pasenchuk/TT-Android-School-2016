package com.ucsoftworks.apptemplate.di;

import android.support.annotation.NonNull;

import com.squareup.otto.Bus;
import com.ucsoftworks.apptemplate.app.App;
import com.ucsoftworks.apptemplate.app.Preferences;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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

    @Provides
    @Singleton
    public Preferences providePreferences() {
        return new Preferences(app);
    }


    @NonNull
    private Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(request);
            }
        };
    }

}
