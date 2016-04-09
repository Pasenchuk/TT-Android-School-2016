package com.thumbtack2016.chat.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pasenchuk Victor on 22.07.15
 */
public class Auth {

    @SerializedName("username")
    private final String username;
    @SerializedName("password")
    private final String password;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}
