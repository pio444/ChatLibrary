package com.example.pio.chatlibrary.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pio.chatlibrary.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pio on 09.09.15.
 */
public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText login;
    private EditText password;
    private EditText confirmPassword;
    private EditText e_mail;
    private EditText first_name;
    private EditText last_name;
    private EditText pesel;
    private EditText city;
    private EditText postal_city;
    private EditText street;
    private EditText home_number;
    private EditText type;
    private RadioButton female;
    private RadioButton male;
    private RadioButton correspondance;
    private RadioButton living;

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
        String registerPostalCity = postal_city.getText().toString();
        String registerStreet = street.getText().toString();
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
        else if (!Validation.validationPostalCity(registerPostalCity)) {
            Toast.makeText(getApplicationContext(), "Invalid Postal City", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationStreet(registerStreet)) {
            Toast.makeText(getApplicationContext(), "Invalid Street", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationHomeNumber(registerHomeNumber)) {
            Toast.makeText(getApplicationContext(), "Invalid Home Number", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        postal_city = (EditText) findViewById(R.id.regiser_Postal_City);
        street = (EditText) findViewById(R.id.regiser_Street);
        home_number = (EditText) findViewById(R.id.regiser_Home_Number);

    }

}
