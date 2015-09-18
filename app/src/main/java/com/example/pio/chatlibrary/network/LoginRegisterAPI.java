package com.example.pio.chatlibrary.network;


import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by pio on 10.09.15.
 */
public interface LoginRegisterAPI {

    //static final String BASE_URL = "http://172.16.20.179:3000/api/v1/accounts";
    static final String BASE_URL = "http://172.16.20.137:3000/api/v1/accounts";

    @POST("/sign_up")
    void sign_up(@Body ModelRegister modelRegister, Callback<ModelRegister> callback);

    @POST("/sign_in")
    void loginToChat(@Query("user") String userName, @Query("password")String userPassword , Callback<String> callback);

    @DELETE("/sign_out")
    void sign_out(@Header("Authorization") String authorization, Callback<ModelSignOut> callback);

    @GET("/logged")
    void logged(@Header("Authorization") String authorization, Callback<ModelUsers> callback);

}
