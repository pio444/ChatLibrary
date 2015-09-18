package com.example.pio.chatlibrary.network;

import com.google.gson.annotations.Expose;

/**
 * Created by bober on 18.09.15.
 */
public class ModelSignOut {

    @Expose
    private String status;

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
