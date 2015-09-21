package com.example.pio.chatlibrary.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pio.chatlibrary.MainActivity;
import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.fragments.DataOfBirthdayDialog;
import com.example.pio.chatlibrary.network.ModelRegister;
import com.example.pio.chatlibrary.network.Retrofit;
import com.example.pio.chatlibrary.util.Network;

/**
 * Created by pio on 09.09.15.
 */
public class RegisterActivity extends AppCompatActivity
        implements Handler.Callback,
        DataOfBirthdayDialog.DataOfBirthday {

    public static final String TAG = RegisterActivity.class.getSimpleName();
    public static final int BACKGROUND_OPERATION = 10;
    public static final int Go_TO_LOGIN_ACTIVITY = 20;

    private EditText login;
    private EditText password;
    private EditText confirmPassword;
    private EditText e_mail;
    private EditText first_name;
    private EditText last_name;
    private EditText pesel;
    private EditText city;
    private EditText postal_code;
    private EditText street;
    private EditText postal_city;
    private EditText home_number;
    private EditText type;
    private RadioButton female;
    private RadioButton male;
    private RadioButton correspondance;
    private RadioButton living;
    private String dataOfBirthday;

    private Handler mHandler;
    private Handler uiHandler;

    public void dataOfBirthday(View view) {
        Log.d(TAG, "dataOfBirthday");
        DataOfBirthdayDialog dialog = new DataOfBirthdayDialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void regiser(View view) {
        Log.d(TAG, "regiser");
        String registerName = login.getText().toString();
        String registerPassword = password.getText().toString();
        String registerConfirmPassword = confirmPassword.getText().toString();
        String registerE_mail = e_mail.getText().toString();
        String registerFirstName = first_name.getText().toString();
        String registerLastName = last_name.getText().toString();
        String registerPesel = pesel.getText().toString();
        String registerCity = city.getText().toString();
        String registerPostalCode = postal_code.getText().toString();
        String registerStreet = street.getText().toString();
        String registerPostalCity = postal_city.getText().toString();
        String registerHomeNumber = home_number.getText().toString();

        String registerSex;
        if (female.isChecked()) {
            registerSex = "female";
        }
        else {
            registerSex = "male";
        }

        String registerType;
        if (correspondance.isChecked()) {
            registerType = "correspondance";
        }
        else {
            registerType = "living";
        }

        Validation validation = new Validation(registerName,
                registerPassword,
                registerConfirmPassword,
                registerE_mail,
                registerFirstName,
                registerLastName,
                registerPesel,
                dataOfBirthday,
                registerCity,
                registerPostalCode,
                registerStreet,
                registerPostalCity,
                registerHomeNumber,
                registerSex,
                registerType);

        String message = validation.validationRegister();
        if (message.equals("true")) {
            ModelRegister model =  validation.creatModelRegister();
            if (Network.isNetworkAvailable(this)) {
                Retrofit retrofit = new Retrofit(getApplicationContext(), this);
                retrofit.sign_up(model, mHandler);
            }
            else {
                Toast.makeText(getApplicationContext(),"There is no internect connection", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        HandlerThread handlerThread = new HandlerThread("BackgroundThread");
        handlerThread.start();
        // Utworzenie nowego obiektu klasy Handler
        mHandler = new Handler(handlerThread.getLooper(), this);

        // Ten Handler będzie działać w wštku głównym
        uiHandler = new Handler(getMainLooper(), this);

        female = (RadioButton) findViewById(R.id.register_female);
        male = (RadioButton) findViewById(R.id.register_male);
        female.setChecked(true);

        correspondance = (RadioButton) findViewById(R.id.register_correspondance);
        living = (RadioButton) findViewById(R.id.register_living);
        correspondance.setChecked(true);

        login = (EditText) findViewById(R.id.register_Name);
        login.setText("kopara");
        password = (EditText) findViewById(R.id.register_Password);
        password.setText("Kopara123!");
        confirmPassword = (EditText) findViewById(R.id.register_Confirm_Password);
        confirmPassword.setText("Kopara123!");
        e_mail = (EditText) findViewById(R.id.register_e_mail);
        e_mail.setText("dupa@op.pl");
        first_name = (EditText) findViewById(R.id.register_FirstName);
        first_name.setText("Kooo");
        last_name = (EditText) findViewById(R.id.register_LastName);
        last_name.setText("Koooooodada");
        pesel = (EditText) findViewById(R.id.register_Pesel);
        pesel.setText("90090909999");
        city = (EditText) findViewById(R.id.register_City);
        city.setText("Kosowo");
        postal_code = (EditText) findViewById(R.id.register_Postal_Code);
        postal_code.setText("58-111");
        street = (EditText) findViewById(R.id.register_Street);
        street.setText("goczowa");
        postal_city = (EditText) findViewById(R.id.register_Postal_City);
        postal_city.setText("Kodw");
        home_number = (EditText) findViewById(R.id.register_Home_Number);
        home_number.setText("1");

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case BACKGROUND_OPERATION:
                Bundle bundle = msg.getData();
                howToast(bundle.getString("message"));
                break;
            case Go_TO_LOGIN_ACTIVITY:
                startLoginActivity();
        }

        return true;

    }

    private void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void howToast(final String message) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setDataOfBirthday(int day, int month, int year) {
        dataOfBirthday = day+"-"+(month+1)+"-"+year;
        Log.d(TAG, dataOfBirthday);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}