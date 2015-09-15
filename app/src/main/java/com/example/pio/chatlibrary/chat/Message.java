package com.example.pio.chatlibrary.chat;

/**
 * Created by pio on 10.09.15.
 */
public class Message {

    private String fromName, message;
    private boolean isSelf;
    private boolean wrong;

    public Message() {
    }

    public Message(String fromName, String message, boolean isSelf, boolean wrong) {
        this.fromName = fromName;
        this.message = message;
        this.isSelf = isSelf;
        this.wrong = wrong;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }

    public boolean isWrong() {
        return wrong;
    }

    public void setWrong(boolean wrong) {
        this.wrong = wrong;
    }
}
