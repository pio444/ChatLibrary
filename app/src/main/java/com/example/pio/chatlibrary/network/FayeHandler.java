package com.example.pio.chatlibrary.network;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.saulpower.fayeclient.FayeClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

/**
 * Created by pio on 14.09.15.
 */
public class FayeHandler {

    private static final URI BASE_URI =  URI.create(String.format("http://%s:9290/faye", "172.16.20.179"));
    private static final String ALL = "/all";
    private static final String USERS = "/users";

    private FayeClient usersChannel;
    private FayeClient allChannel;

    private ListenerFaye listenerFaye;

    public FayeHandler(Activity activity){

        usersChannel = new FayeClient(new Handler(),BASE_URI,USERS);
        allChannel = new FayeClient(new Handler(),BASE_URI, ALL);
        this.listenerFaye = (ListenerFaye)activity;

        usersChannel.connectToServer(new JSONObject());
        allChannel.connectToServer(new JSONObject());

        listenForUsers();
        listenForAll();

    }

    public void sendMessageUsersChannel(JSONObject jsonObject){
        usersChannel.sendMessage(jsonObject);
    }
    public void sendMessageAllChannel(JSONObject jsonObject){
        allChannel.sendMessage(jsonObject);
    }

    private void listenForUsers(){
        usersChannel.setFayeListener(new FayeClient.FayeListener() {
            @Override
            public void connectedToServer() {

                Log.i("FAYE", "connected");
            }

            @Override
            public void disconnectedFromServer() {

            }

            @Override
            public void subscribedToChannel(String subscription) {

            }

            @Override
            public void subscriptionFailedWithError(String error) {

            }

            @Override
            public void messageReceived(JSONObject json) {
                listenerFaye.getDataFromChannel(json,"USERS");
                try {
                    Log.i("REVIVED", json.getString("author") + json.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void listenForAll(){
        allChannel.setFayeListener(new FayeClient.FayeListener() {
            @Override
            public void connectedToServer() {
                Log.i("FAYE", "connected");
            }

            @Override
            public void disconnectedFromServer() {

            }

            @Override
            public void subscribedToChannel(String subscription) {

            }

            @Override
            public void subscriptionFailedWithError(String error) {

            }

            @Override
            public void messageReceived(JSONObject json) {
                listenerFaye.getDataFromChannel(json, "ALL");
                try {
                    Log.i("RECIVED", json.getString("author") + " " + json.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
