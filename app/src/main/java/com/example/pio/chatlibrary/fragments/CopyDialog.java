package com.example.pio.chatlibrary.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.pio.chatlibrary.R;

/**
 * Created by bober on 15.09.15.
 */
public class CopyDialog extends DialogFragment {

    public static final String TAG = CopyDialog.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String message = getArguments().getString("message");
        Log.d(TAG, message);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.copyDialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Log.d(TAG, String.valueOf(which));
                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label", message);
                        clipboard.setPrimaryClip(clip);

                    }
                });


        // Create the AlertDialog object and return it
        return builder.create();

    }
}
