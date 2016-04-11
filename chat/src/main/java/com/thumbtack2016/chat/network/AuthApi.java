package com.thumbtack2016.chat.network;


import com.thumbtack2016.chat.network.models.Auth;
import com.thumbtack2016.chat.network.models.Token;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Pasenchuk Victor on 17.07.15
 */
public interface AuthApi {

    @POST("session/")
    Observable<Token> getToken(@Body Auth auth);

}
