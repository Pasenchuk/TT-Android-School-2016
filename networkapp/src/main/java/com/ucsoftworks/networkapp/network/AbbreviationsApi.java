package com.ucsoftworks.networkapp.network;

import com.ucsoftworks.networkapp.network.models.SearchResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pasencukviktor on 20/03/16
 */
public interface AbbreviationsApi {

    @GET("dictionary.py")
    Observable<SearchResponse> getResponse(@Query("sf") String searchString);

}
