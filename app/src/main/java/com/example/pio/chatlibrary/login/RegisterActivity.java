package com.example.pio.chatlibrary.login;

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

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.network.ModelRegister;
import com.example.pio.chatlibrary.network.RetrofitHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by pio on 09.09.15.
 */
public class RegisterActivity extends AppCompatActivity implements Handler.Callback {

    public static final String TAG = RegisterActivity.class.getSimpleName();
    public static final int BACKGROUND_OPERATION = 10;

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

    private Handler mHandler;
    private Handler uiHandler;

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

        if (!Validation.validationName(registerName)) {
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationPassword(registerPassword, registerConfirmPassword)) {
            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationE_mail(registerE_mail)) {
            Toast.makeText(getApplicationContext(), "Invalid E-mail", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationFirstName(registerFirstName)) {
            Toast.makeText(getApplicationContext(), "Invalid First Name", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationLastName(registerLastName)) {
            Toast.makeText(getApplicationContext(), "Invalid Last Name", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationPesel(registerPesel)) {
            Toast.makeText(getApplicationContext(), "Invalid Pesel", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationCity(registerCity)) {
            Toast.makeText(getApplicationContext(), "Invalid City", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationPostalCode(registerPostalCode)) {
            Toast.makeText(getApplicationContext(), "Invalid Postal Code", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationStreet(registerStreet)) {
            Toast.makeText(getApplicationContext(), "Invalid Street", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationPostalCity(registerPostalCity)) {
            Toast.makeText(getApplicationContext(), "Invalid Postal City", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationHomeNumber(registerHomeNumber)) {
            Toast.makeText(getApplicationContext(), "Invalid Home Number", Toast.LENGTH_SHORT).show();
        }
        else {

            ModelRegister model = new ModelRegister();
            model.setUser(registerName);
            model.setPassword(registerPassword);
            model.setPasswordConfirmation(registerConfirmPassword);

            /***Emails attributes***/
            List<ModelRegister.EmailsAttribute> emailsAttributes = new ArrayList<ModelRegister.EmailsAttribute>();
            ModelRegister.EmailsAttribute email_sAttributes = new ModelRegister.EmailsAttribute();
            email_sAttributes.setAddress(registerE_mail);
            emailsAttributes.add(email_sAttributes);
            model.setEmailsAttributes(emailsAttributes);

            /***Person attributes***/
            ModelRegister.PersonAttributes personAttributes = new ModelRegister.PersonAttributes();
            personAttributes.setPESEL(registerPesel);
            personAttributes.setDateOfBirth("13-12-1999");
            personAttributes.setFirstName(registerFirstName);
            personAttributes.setLastName(registerLastName);
            personAttributes.setSex(registerSex);

                /***Person attr***/
                List<ModelRegister.AddressesAttribute> addressesAttributes = new ArrayList<ModelRegister.AddressesAttribute>();
                ModelRegister.AddressesAttribute addresses_Attributes = new ModelRegister.AddressesAttribute();
                addresses_Attributes.setCity(registerCity);
                addresses_Attributes.setStreet(registerStreet);
                addresses_Attributes.setHomeNumber(registerHomeNumber);
                addresses_Attributes.setPostalCode(registerPostalCode);
                addresses_Attributes.setPostalCity(registerPostalCity);
                addresses_Attributes.setAddressType(registerType);
                addressesAttributes.add(addresses_Attributes);
            personAttributes.setAddressesAttributes(addressesAttributes);
            model.setPersonAttributes(personAttributes);

            RetrofitHandler retrofit = new RetrofitHandler();
            retrofit.getLoginRegisterAPI().sign_up(model, new Callback<ModelRegister>() {
                @Override
                public void success(ModelRegister modelRegister, Response response) {
                    Log.d(TAG + "/retrofit", response.getUrl());
                    Log.d(TAG + "/retrofit", String.valueOf(response.getStatus()));
                    Log.d(TAG + "/retrofit", String.valueOf(response.getStatus()));
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG + "/retrofit", error.toString());
                    if (error.toString().matches(".*failed to connect.*")) {
                        Message message = mHandler.obtainMessage(BACKGROUND_OPERATION);
                        Bundle bundle = new Bundle();
                        bundle.putString("message", getResources().getString(R.string.failed_to_connect));
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }
                    else {
                        String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                        Log.e("failure", json.toString());
                        String msg = json.toString().replaceFirst("\\{\"errors\":\\[\"", "");
                        msg = msg.replaceFirst("\"\\]\\}", "");
                        msg = msg.replaceFirst("\",\"", ", ");
                        Message message = mHandler.obtainMessage(BACKGROUND_OPERATION);
                        Bundle bundle = new Bundle();
                        bundle.putString("message", msg);
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }

                }
            });
            /*Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();*/
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

        login = (EditText) findViewById(R.id.regiser_Name);
        password = (EditText) findViewById(R.id.regiser_Password);
        confirmPassword = (EditText) findViewById(R.id.regiser_Confirm_Password);
        e_mail = (EditText) findViewById(R.id.regiser_e_mail);
        first_name = (EditText) findViewById(R.id.regiser_FirstName);
        last_name = (EditText) findViewById(R.id.regiser_LastName);
        pesel = (EditText) findViewById(R.id.regiser_Pesel);
        city = (EditText) findViewById(R.id.regiser_City);
        postal_code = (EditText) findViewById(R.id.regiser_Postal_Code);
        street = (EditText) findViewById(R.id.regiser_Street);
        postal_city = (EditText) findViewById(R.id.regiser_Postal_City);
        home_number = (EditText) findViewById(R.id.regiser_Home_Number);

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case BACKGROUND_OPERATION:
                Bundle bundle = msg.getData();
                howToast(bundle.getString("message"));
        }

        return true;

    }

    private void howToast(final String message) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
