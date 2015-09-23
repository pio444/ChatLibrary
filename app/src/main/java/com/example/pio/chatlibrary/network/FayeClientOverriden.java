package com.example.pio.chatlibrary.network;


import android.net.Uri;
import android.os.Handler;

import com.saulpower.fayeclient.*;

import org.json.JSONObject;

import java.net.URI;

/**
 * Created by pio on 23.09.15.
 */
public class FayeClientOverriden extends com.saulpower.fayeclient.FayeClient {

    public FayeClientOverriden(Handler handler, URI uri, String channel){
        super(handler,uri,channel);

    }
    @Override
    public void publish(JSONObject jsonObject, JSONObject extension){}

}
