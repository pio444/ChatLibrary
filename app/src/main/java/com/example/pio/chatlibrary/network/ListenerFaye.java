package com.example.pio.chatlibrary.network;

import org.json.JSONObject;

/**
 * Created by pio on 14.09.15.
 */
public interface ListenerFaye {

    void getDataFromChannel(JSONObject jsonObject, String messageType);
}
