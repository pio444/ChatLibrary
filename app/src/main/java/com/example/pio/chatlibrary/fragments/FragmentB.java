package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pio.chatlibrary.MyApplication;
import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.chat.Message;
import com.example.pio.chatlibrary.chat.MessagesListAdapter;
import com.example.pio.chatlibrary.chat.User;
import com.example.pio.chatlibrary.network.ActivityListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pio on 09.09.15.
 */
public class FragmentB extends Fragment {

    public interface PrivetListener {
        void sendPrivetMessage(String user, String message);
    }

    public static final String TAG = FragmentB.class.getSimpleName();
    private List<String> privatePersonList;
    private List<User>  usersList;
    private ListView listView;
    private Spinner spinner;
    private MessagesListAdapter messagesListAdapter;
    private List<Message> listMessages;
    private MyApplication myApplication;
    private ArrayAdapter<String> adapter;
    private Button send;
    private EditText inputMSG;
    private PrivetListener mCallback;


    public FragmentB(){

        privatePersonList = new ArrayList<>();

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

        messagesListAdapter = new MessagesListAdapter(getActivity(),listMessages);
        listView.setAdapter(messagesListAdapter);

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_layout, privatePersonList);
        spinner.setAdapter(adapter);

        inputMSG = (EditText) view.findViewById(R.id.inputMsg);

        send = (Button) view.findViewById(R.id.btnSendPrivate);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = null;
                try {
                    name = spinner.getSelectedItem().toString();
                    Log.d(TAG, name);
                    String message = inputMSG.getText().toString();
                    if (!message.equals("")) {
                        mCallback.sendPrivetMessage(name, message);
                    }
                }
                catch (NullPointerException e) {
                    Log.d(TAG, "No selected user");
                    Toast.makeText(getActivity().getApplicationContext(),
                            getResources().getString(R.string.no_selected_user), Toast.LENGTH_SHORT).show();
                }
                inputMSG.setText("");
            }
        });

        return view;
    }
    public void updatePrivateList(){

        privatePersonList.clear();

        for (int i = 0 ; i < usersList.size(); i++){
            if(usersList.get(i).isUserOnPrivateChat())
                privatePersonList.add(usersList.get(i).getUserName());
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myApplication = (MyApplication)getActivity().getApplication();
        usersList = myApplication.getUsersList();

        try {
            mCallback = (PrivetListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

}
