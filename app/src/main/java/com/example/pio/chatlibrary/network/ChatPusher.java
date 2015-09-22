package com.example.pio.chatlibrary.network;

import android.util.Log;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PresenceChannel;
import com.pusher.client.channel.PresenceChannelEventListener;
import com.pusher.client.channel.User;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;

import java.util.Set;

/**
 * Created by pio on 22.09.15.
 */
public class ChatPusher {

    private static final String END_POINT = "http://172.16.20.179:9290/faye";
    private Pusher pusher;
    private Channel channel;

    public ChatPusher(){

       // HttpAuthorizer httpAuthorizer = new HttpAuthorizer(END_POINT);
        PusherOptions pusherOptions = new PusherOptions().setHost(END_POINT);
        pusher = new Pusher("189a446a37d16f2cf7e9");

    }
    public void connectPusher(){
        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.d("status", change.getCurrentState().toString());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                //e.printStackTrace();
            }
        });
    }
    public void listenOnChannel() {
        pusher.disconnect();


        channel = pusher.subscribe("http://172.16.20.179:9290/faye/all", new ChannelEventListener() {
            @Override
            public void onSubscriptionSucceeded(String channelName) {
                if (channelName.length()==0)
                {
                    channelName.toLowerCase();
                }
            }

            @Override
            public void onEvent(String channelName, String eventName, String data) {
                if (channelName.length()==0)
                {
                    channelName.toLowerCase();
                }
            }
        });

    }
}
