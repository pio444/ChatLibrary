package com.example.pio.chatlibrary;

import android.app.Application;

import com.example.pio.chatlibrary.chat.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pio on 15.09.15.
 */
public class MyApplication extends Application {

    private List<User> usersList;

    @Override
    public void onCreate() {
        super.onCreate();
        usersList = new ArrayList<>();
    }
    public List<User> getUsersList(){
        return usersList;
    }
}
