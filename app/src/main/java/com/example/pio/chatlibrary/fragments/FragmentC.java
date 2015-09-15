package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.chat.User;
import com.example.pio.chatlibrary.network.ActivityListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pio on 09.09.15.
 */
public class FragmentC extends Fragment {


    private List<User> usersList;
    private HashMap<String, User> privateUsersMap;
    private MyAdapter myAdapter;
    private ListView usersListView;
    private CheckBox checkBox;
    private ActivityListener activityListener;

    public FragmentC() {

        usersList = new ArrayList<>();
        privateUsersMap = new HashMap<>();
        usersList.add(new User("tomek", true));
        usersList.add(new User("romek", true));
        usersList.add(new User("anna", true));
        usersList.add(new User("wacław", true));


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAdapter = new MyAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        usersListView = (ListView) view.findViewById(R.id.list_view_users);
        usersListView.setAdapter(myAdapter);

        return view;
    }

    public void updateLoggedList(String[] userNames) {
        for (String userName : userNames) {
            usersList.add(new User(userName, true));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityListener  = (ActivityListener)activity;
    }

    class MyAdapter extends BaseAdapter {

        public MyAdapter() {

        }

        @Override
        public int getCount() {
            return usersList.size();
        }

        @Override
        public Object getItem(int position) {
            return usersList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return usersList.indexOf(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getActivity().getLayoutInflater().inflate(R.layout.single_user, parent, false);
            checkBox = (CheckBox) view.findViewById(R.id.check_box_users);
            checkBox.setTag(position);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = (Integer) buttonView.getTag();
                    if (!isChecked)
                        privateUsersMap.remove(usersList.get(position).getUserName());
                    else
                        privateUsersMap.put(usersList.get(position).getUserName(), usersList.get(position));

                    activityListener.getPrivateUserMap(privateUsersMap);

                }
            });
            TextView userName = (TextView) view.findViewById(R.id.text_view_user);
            userName.setText(usersList.get(position).getUserName());
            return view;

        }
    }
}
