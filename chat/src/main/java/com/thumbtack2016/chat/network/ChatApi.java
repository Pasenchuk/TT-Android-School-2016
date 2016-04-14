package com.thumbtack2016.chat.network;


import com.squareup.picasso.Downloader;
import com.thumbtack2016.chat.network.models.Auth;
import com.thumbtack2016.chat.network.models.Token;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Pasenchuk Victor on 17.07.15
 */
public interface ChatApi {

    @POST("session/")
    Observable<Token> getToken(@Body Auth auth);

    @FormUrlEncoded
    @POST("device/gcm/")
    Observable<Void> gcmRegister(@Field("name") String name, @Field("device_id") String device_id, @Field("registration_id") String registration_id);
}
