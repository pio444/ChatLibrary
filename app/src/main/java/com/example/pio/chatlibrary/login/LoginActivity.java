package com.example.pio.chatlibrary.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.TabBarActivity;

/**
 * Created by pio on 09.09.15.
 */
public class LoginActivity extends AppCompatActivity {


    private EditText loginEditText;
    private EditText passwordEditText;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginEditText = (EditText) findViewById(R.id.editTextUserName);
        passwordEditText = (EditText)findViewById(R.id.editTextUserPassword);
    }


    public void singIn(View view) {


        if (!Validation.validationName(loginEditText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
        }
        else if (!Validation.validationPassword(passwordEditText.getText().toString(), passwordEditText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
        }
//        else if( /* jakies warunek*/ ){
//             obsługa złych danych do logowania, komunikat (podales zly login/haslo)
//        }
//        else
//             przejdz do ekranu czata
        else {
            Intent i = new Intent(this, TabBarActivity.class);
            startActivity(i);
        }
    }
}
