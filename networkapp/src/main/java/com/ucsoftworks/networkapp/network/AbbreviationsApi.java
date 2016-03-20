package com.ucsoftworks.networkapp.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pasencukviktor on 20/03/16
 */
public interface AbbreviationsApi {

    @GET("dictionary.py")
    Observable<Void> getResponse(@Query("sf") String searchString);

}
