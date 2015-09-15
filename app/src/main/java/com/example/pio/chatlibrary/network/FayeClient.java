package com.example.pio.chatlibrary.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.pio.chatlibrary.TabBarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by bober on 14.09.15.
 */
public class FayeClient implements com.saulpower.fayeclient.FayeClient.FayeListener {

    public static final String TAG = FayeClient.class.getSimpleName();
    private final String URI = "http://172.16.20.179:9290/faye";
    //private final String URI = "http://172.16.20.137:9290/faye";
    private String CHANNEL;
    private Handler handler;
    private com.saulpower.fayeclient.FayeClient mClient;

    public FayeClient(String CHANNEL, Handler handler) {
        this.CHANNEL = CHANNEL;
        this.handler = handler;
    }

    public void start() {
        Log.i(TAG, "Starting Web Socket");

        try {
            mClient = new com.saulpower.fayeclient.FayeClient(handler, new URI(URI), CHANNEL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JSONObject ext = new JSONObject();
        mClient.setFayeListener(this);
        mClient.connectToServer(ext);

    }


    @Override
    public void connectedToServer() {
        Log.i(TAG, "Connected to Server");
    }

    @Override
    public void disconnectedFromServer() {
        Log.i(TAG, "Disonnected to Server");
    }

    @Override
    public void subscribedToChannel(String subscription) {
        Log.i(TAG, String.format("Subscribed to channel %s on Faye", subscription));
    }

    @Override
    public void subscriptionFailedWithError(String error) {
        Log.i(TAG, String.format("Subscription failed with error: %s", error));
    }

    @Override
    public void messageReceived(JSONObject json) {
        Log.i(TAG, String.format("Received message %s", json.toString()));
        try {
            String author = json.getString("author");//.replaceAll("\"", "");
            String wiadomosc = json.getString("message");//.replaceAll("\"", "");
            Log.i(TAG, String.format("Received message name %s", author));
            Log.i(TAG, String.format("Received message %s", wiadomosc));
            Message message = handler.obtainMessage(TabBarActivity.BACKGROUND_OPERATION);
            Bundle bundle = new Bundle();
            bundle.putString("login", author);
            bundle.putString("message", wiadomosc);
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void send(String author, String message) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", author);
        jsonObject.put("message", message);
        mClient.sendMessage(jsonObject);
    }
}
