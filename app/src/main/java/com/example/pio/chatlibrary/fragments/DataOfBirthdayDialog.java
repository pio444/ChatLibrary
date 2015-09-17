package com.example.pio.chatlibrary.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.TabBarActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bober on 17.09.15.
 */
public class DataOfBirthdayDialog extends DialogFragment {

    private DataOfBirthday mCallback;
    public interface DataOfBirthday {
        void setDataOfBirthday(int day, int month, int year);
    }

    public static final String TAG = DataOfBirthdayDialog.class.getSimpleName();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DataOfBirthday) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_data_of_birthday, null);

        final int[] dayOfMonth = getResources().getIntArray(R.array.dayOfMonth);

        final NumberPicker day = (NumberPicker) view.findViewById(R.id.numberPicker);
        final NumberPicker year = (NumberPicker) view.findViewById(R.id.numberPicker3);
        final NumberPicker month = (NumberPicker) view.findViewById(R.id.numberPicker2);

        day.setMinValue(1);
        day.setMaxValue(dayOfMonth[0]);

        month.setMinValue(0);
        month.setMaxValue(11);
        month.setDisplayedValues(getResources().getStringArray(R.array.montsOfYear));
        month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d(TAG, String.valueOf(newVal));
                switch (newVal) {
                    case 0:
                        day.setMaxValue(dayOfMonth[0]);
                        break;
                    case 1:
                        int rok = year.getValue();
                        Log.d(TAG, String.valueOf(rok));
                        if ((rok%4 == 0 && rok%100 != 0) || rok%400 == 0) {
                            day.setMaxValue(dayOfMonth[12]);
                        }
                        else {
                            day.setMaxValue(dayOfMonth[1]);
                        }
                        break;
                    case 2:
                        day.setMaxValue(dayOfMonth[2]);
                        break;
                    case 3:
                        day.setMaxValue(dayOfMonth[3]);
                        break;
                    case 4:
                        day.setMaxValue(dayOfMonth[4]);
                        break;
                    case 5:
                        day.setMaxValue(dayOfMonth[5]);
                        break;
                    case 6:
                        day.setMaxValue(dayOfMonth[6]);
                        break;
                    case 7:
                        day.setMaxValue(dayOfMonth[7]);
                        break;
                    case 8:
                        day.setMaxValue(dayOfMonth[8]);
                        break;
                    case 9:
                        day.setMaxValue(dayOfMonth[9]);
                        break;
                    case 10:
                        day.setMaxValue(dayOfMonth[10]);
                        break;
                    case 11:
                        day.setMaxValue(dayOfMonth[11]);
                        break;
                }
            }
        });

        year.setMinValue(1900);
        DateFormat df = new SimpleDateFormat("yyyy");
        String now = df.format(new Date());
        Log.d(TAG, now);
        year.setMaxValue(Integer.parseInt(now));

        year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int m = month.getValue();
                if (m == 1) {
                    if ((newVal%4 == 0 && newVal%100 != 0) || newVal%400 == 0) {
                        day.setMaxValue(dayOfMonth[12]);
                    }
                    else {
                        day.setMaxValue(dayOfMonth[1]);
                    }
                }
            }
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...rz
                        mCallback.setDataOfBirthday(day.getValue(), month.getValue(), year.getValue());
                    }
                })
                .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().cancel();
                    }
                });


        // Create the AlertDialog object and return it
        return builder.create();

    }

}
