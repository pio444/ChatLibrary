package com.example.pio.chatlibrary.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.TabBarActivity;
import com.example.pio.chatlibrary.network.Retrofit;
import com.example.pio.chatlibrary.network.RetrofitHandler;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by pio on 09.09.15.
 */
public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = (EditText) findViewById(R.id.editTextUserName);
        loginEditText.setText("normalny");
        passwordEditText = (EditText) findViewById(R.id.editTextUserPassword);
        passwordEditText.setText("Dupek123!");

    }

    public void singIn(View view) {

//        Intent intent = new Intent(LoginActivity.this, TabBarActivity.class);
//        //intent.putExtra("TOKEN", token);
//        intent.putExtra("LOGIN", loginEditText.getText().toString());
//        intent.putExtra("LOGIN", loginEditText.getText().toString());
//        startActivity(intent);

        Validation validation = new Validation(loginEditText.getText().toString(), passwordEditText.getText().toString());
        String message = validation.validationLogin();
        if (message.equals("true")) {
            Retrofit retrofit = new Retrofit(getApplicationContext(), this);
            retrofit.sign_in(loginEditText.getText().toString(), passwordEditText.getText().toString());
        }
        else {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

    }


}