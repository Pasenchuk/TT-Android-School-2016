package com.thumbtack2016.chat.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pasenchuk Victor on 19.07.15
 */
public class Token {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
