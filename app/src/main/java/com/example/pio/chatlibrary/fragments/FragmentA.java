package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.chat.Message;
import com.example.pio.chatlibrary.chat.MessagesListAdapter;
import com.example.pio.chatlibrary.network.ActivityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pio on 09.09.15.
 */
public class FragmentA extends Fragment {

    private MessagesListAdapter messagesListAdapter;
    private EditText editTextMessage;
    private Button sendButton;
    private List<Message> listMessages;
    private ListView listView;
    private ActivityListener activityListener;


    public FragmentA() {

        listMessages = new ArrayList<>();
        listMessages.add(new Message("Ja", "jakas wiadomosc1", true));
        listMessages.add(new Message("Tom", "jakas wiadomosc2", false));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        editTextMessage = (EditText) view.findViewById(R.id.inputMsg);

        sendButton = (Button) view.findViewById(R.id.btnSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.getUserMessage(editTextMessage.getText().toString());
                listMessages.add(new Message("Me", editTextMessage.getText().toString(), true));
                listView.invalidateViews();

            }
        });
        listView = (ListView) view.findViewById(R.id.list_view_messages);
        listView.setAdapter(messagesListAdapter);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.invalidateViews();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityListener = (ActivityListener) activity;
        messagesListAdapter = new MessagesListAdapter(activity, listMessages);
    }

    public void updateMessagesList(String author, String message, boolean isSelf) {

        listMessages.add(new Message(author, message, isSelf));
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.invalidateViews();
            }
        });


    }
}
