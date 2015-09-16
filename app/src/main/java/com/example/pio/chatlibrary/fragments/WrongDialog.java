package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.pio.chatlibrary.R;

import org.json.JSONException;

/**
 * Created by bober on 15.09.15.
 */
public class WrongDialog extends DialogFragment {

    private SendAgain mCallback;
    public interface SendAgain {
        void sendAgain(int position, String message) throws JSONException;
    }

    public static final String TAG = WrongDialog.class.getSimpleName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SendAgain) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String message = getArguments().getString("message");
        final int position = getArguments().getInt("position");
        Log.d(TAG, message);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.wrongDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                Log.d(TAG, String.valueOf(which));
                if (which == 0) {
                    try {
                        mCallback.sendAgain(position, message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if (which == 1) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", message);
                    clipboard.setPrimaryClip(clip);
                }

            }
        });


        // Create the AlertDialog object and return it
        return builder.create();

    }

}
