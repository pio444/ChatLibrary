package com.example.pio.chatlibrary.network;


import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by pio on 10.09.15.
 */
public class RetrofitHandler  {


    private LoginRegisterAPI loginRegisterAPI;


    public RetrofitHandler() {
        initRetrofit();

    }

    private void initRetrofit() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(LoginRegisterAPI.BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL).
                setConverter(new GsonConverter(new GsonBuilder().create())).build();
        loginRegisterAPI = restAdapter.create(LoginRegisterAPI.class);

    }

    public LoginRegisterAPI getLoginRegisterAPI(){
        return loginRegisterAPI;
    }


}
