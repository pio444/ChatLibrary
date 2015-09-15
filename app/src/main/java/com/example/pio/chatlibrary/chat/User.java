package com.example.pio.chatlibrary.chat;

/**
 * Created by pio on 15.09.15.
 */
public class User {

    private String userName;
    private boolean isLogged;

    public User(String userName, boolean isLogged) {
        this.userName = userName;
        this.isLogged = isLogged;
    }

    public void setLogged(boolean logOrNot) {
        this.isLogged = logOrNot;
    }

    public boolean isUserLogged() {
        return isLogged;
    }



    public String getUserName() {
        return userName;
    }
}
