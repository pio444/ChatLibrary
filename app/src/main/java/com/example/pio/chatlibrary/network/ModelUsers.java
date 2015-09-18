package com.example.pio.chatlibrary.network;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bober on 18.09.15.
 */
public class ModelUsers {


    @Expose
    private List<String> users = new ArrayList<String>();

    /**
     *
     * @return
     * The users
     */
    public List<String> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     * The users
     */
    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ModelUsers{" +
                "users=" + users +
                '}';
    }
}
