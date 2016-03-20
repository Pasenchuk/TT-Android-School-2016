package com.ucsoftworks.networkapp.network;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by pasencukviktor on 20/03/16
 */
public interface Api {

    @GET("/")
    Observable<Void> getResponse();

}
