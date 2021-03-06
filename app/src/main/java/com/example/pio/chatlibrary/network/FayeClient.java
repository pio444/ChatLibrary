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
    private String TOKEN;
    private String user;
    private boolean listener;
    private String message;

    public FayeClient(String CHANNEL, Handler handler, String TOKEN, String user) {
        this.CHANNEL = CHANNEL;
        this.handler = handler;
        this.TOKEN = TOKEN;
        this.user = user;
        this.listener = false;
    }

    public void start() {
        Log.i(TAG, "Starting Web Socket");

        try {
            mClient = new com.saulpower.fayeclient.FayeClient(handler, new URI(URI), CHANNEL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        JSONObject ext = new JSONObject();
        try {
            ext.put("authToken", TOKEN);
            ext.put("user", user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        if (listener) {
            try {
                send(user, message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listener = false;


    }

    @Override
    public void subscriptionFailedWithError(String error) {
        Log.i(TAG, String.format("Subscription failed with error: %s", error));
    }

    @Override
    public void messageReceived(JSONObject json) {
        Log.i(TAG, String.format("Received message kanal: %s %s", CHANNEL, json.toString()));
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
            //e.printStackTrace();
            try {
                String user = json.getString("user");
                Boolean activity = Boolean.parseBoolean(json.getString("activity"));
                Message message = handler.obtainMessage(TabBarActivity.USER_OPERATION);
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                bundle.putBoolean("activity", activity);
                message.setData(bundle);
                handler.sendMessage(message);
            } catch (JSONException e1) {
                //e1.printStackTrace();
            }
        }
    }

    public void send(String author, String message) throws JSONException {


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("author", author);
            jsonObject.put("message", message);
            mClient.sendMessage(jsonObject);



    }

    public void unsubscribe() {
        mClient.unsubscribe();
        mClient.setFayeListener(null);
        mClient.closeWebSocketConnection();

    }

    public String getNameChannel() {
        return CHANNEL;
    }

    public void run(String message) {
        this.message = message;
        this.listener = true;
        start();
    }
}
