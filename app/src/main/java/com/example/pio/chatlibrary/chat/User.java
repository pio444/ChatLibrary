package com.example.pio.chatlibrary.chat;

/**
 * Created by pio on 15.09.15.
 */
public class User {

    private String userName;
    private boolean isLogged;
    private boolean isOnPrivateChat;

    public User(String userName, boolean isLogged) {
        this.userName = userName;
        this.isLogged = isLogged;
    }

    public void setLogged(boolean logOrNot) {
        isLogged = logOrNot;
    }
    public void setOnPrivateChat(boolean onPrivateChat){
        isOnPrivateChat = onPrivateChat;
    }
    public boolean isUserOnPrivateChat(){
        return isOnPrivateChat;
    }
    public boolean isUserLogged() {
        return isLogged;
    }



    public String getUserName() {
        return userName;
    }

    @Override
    public boolean equals(Object object){
        User other = (User)object;
        if (this.userName.equals(other.getUserName()))
            return true;
        return false;
    }
}
