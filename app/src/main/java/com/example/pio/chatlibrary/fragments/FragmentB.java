package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.chat.Message;
import com.example.pio.chatlibrary.chat.MessagesListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pio on 09.09.15.
 */
public class FragmentB extends Fragment {


    private List<String> personList;
    private ListView listView;
    private Spinner spinner;
    private MessagesListAdapter messagesListAdapter;
    private List<Message> listMessages;



    public FragmentB(){

        personList = new ArrayList<>();
        personList.add("Olek");
        personList.add("Tomek");
        personList.add("Kasia");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMessages = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container,false);

        listView = (ListView)view.findViewById(R.id.list_view_messages_fragmentB);
        spinner = (Spinner)view.findViewById(R.id.person_spinner);

        /*for (int i=0; i <500;i++){
            listMessages.add(new Message("Ja", "jakas wiadomosc1",true));
            listMessages.add(new Message("Tom", "jakas wiadomosc2",false));
        }*/

        messagesListAdapter = new MessagesListAdapter(getActivity(),listMessages);
        listView.setAdapter(messagesListAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_layout,personList);
        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
