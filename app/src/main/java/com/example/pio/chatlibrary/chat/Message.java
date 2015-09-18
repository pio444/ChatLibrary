package com.example.pio.chatlibrary.chat;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pio on 10.09.15.
 */
public class Message implements Parcelable {

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

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fromName);
        dest.writeString(message);
        dest.writeByte((byte) (isSelf ? 1 : 0));
        dest.writeByte((byte) (wrong ? 1 : 0));
    }

    private Message(Parcel in) {
        fromName = in.readString();
        message = in.readString();
        isSelf = in.readByte() != 0;
        wrong = in.readByte() != 0;
    }
}
