package com.example.pio.chatlibrary.network;

import com.example.pio.chatlibrary.chat.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pio on 14.09.15.
 */
public interface ActivityListener {

    void getUserMessage(String message);
    void getPrivateUserMap(HashMap<String, User> privateUserList);

}
