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

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pio on 09.09.15.
 */
public class FragmentA extends Fragment {

    SendMessage mCallback;
    public interface SendMessage {
        void send(String message) throws JSONException;
    }

    private MessagesListAdapter messagesListAdapter;
    private List<Message> listMessages;
    private ListView listView;
    private Button button;
    private EditText editText;


    public FragmentA(){

        listMessages = new ArrayList<>();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);

        editText = (EditText) view.findViewById(R.id.inputMsg);
        button = (Button) view.findViewById(R.id.btnSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    try {
                        mCallback.send(editText.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        listView = (ListView)view.findViewById(R.id.list_view_messages);

        listView.setAdapter(messagesListAdapter);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        messagesListAdapter = new MessagesListAdapter(activity,listMessages);

        try {
            mCallback = (SendMessage) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    public void addMessage(String login, String message, boolean ja) {
        listMessages.add(new Message(login, message, ja));
        listView.invalidateViews();
    }
}
