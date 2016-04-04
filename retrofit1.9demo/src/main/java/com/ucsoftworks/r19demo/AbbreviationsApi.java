package com.ucsoftworks.r19demo;


import com.ucsoftworks.r19demo.models.SearchResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;


/**
 * Created by pasencukviktor on 20/03/16
 */
public interface AbbreviationsApi {

    @GET("dictionary.py")
    void getResponse(@Query("sf") String searchString, Callback<List<SearchResponse>> callback);


    List<SearchResponse> getResponseSync(@Query("sf") String searchString);

    @GET("dictionary.py")
    Observable<List<SearchResponse>> getObservableResponse(@Query("sf") String searchString);

}
