package com.example.pio.chatlibrary.util;

import com.example.pio.chatlibrary.network.FayeHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pio on 14.09.15.
 */
public class Sender {

    public static void  sendMessage(String author, String message, FayeHandler fayeHandler){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("author", author);
            jsonObject.put("message", message);
            fayeHandler.sendMessageAllChannel(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

