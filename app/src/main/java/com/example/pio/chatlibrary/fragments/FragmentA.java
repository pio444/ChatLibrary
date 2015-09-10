package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.chat.Message;
import com.example.pio.chatlibrary.chat.MessagesListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pio on 09.09.15.
 */
public class FragmentA extends Fragment {

    private MessagesListAdapter messagesListAdapter;
    private List<Message> listMessages;
    private ListView listView;


    public FragmentA(){

        listMessages = new ArrayList<>();


        for (int i=0; i <500;i++){
            listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
            listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        }
        listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
        listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
        listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
        listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
        listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
        listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
        listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        listMessages.add(new Message("Ja", "jakas wiadomosc333333333333333333333333333333333333333333333333333333333333333333333333333333333331",true));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        listView = (ListView)view.findViewById(R.id.list_view_messages);

        listView.setAdapter(messagesListAdapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        messagesListAdapter = new MessagesListAdapter(activity,listMessages);
    }
}
