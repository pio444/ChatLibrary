package com.example.pio.chatlibrary.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by pio on 10.09.15.
 */
public interface LoginRegisterAPI {

    static final String BASE_URL = "http://172.16.20.179:3000/api/v1/accounts";

    @POST("/sign_up")
    void sign_up(@Body ModelRegister modelRegister, Callback<ModelRegister> callback);

}
