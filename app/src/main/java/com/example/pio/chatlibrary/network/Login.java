package com.example.pio.chatlibrary.network;

/**
 * Created by pio on 10.09.15.
 */
public class Login {

    private String user;
    private String password;


    public Login(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }

}
