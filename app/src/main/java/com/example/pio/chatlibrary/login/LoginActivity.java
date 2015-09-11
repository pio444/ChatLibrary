package com.example.pio.chatlibrary.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.TabBarActivity;
import com.example.pio.chatlibrary.network.RetrofitHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by pio on 09.09.15.
 */
public class LoginActivity extends AppCompatActivity {


    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = (EditText) findViewById(R.id.editTextUserName);
        loginEditText.setText("xxx104");
        passwordEditText = (EditText) findViewById(R.id.editTextUserPassword);
        passwordEditText.setText("Zaq!12wsx");

    }
    public void singIn(View view) {

        if (!Validation.validationName(loginEditText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
        } else if (!Validation.validationPassword(passwordEditText.getText().toString(), passwordEditText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
        } else {
            RetrofitHandler retrofitHandler = new RetrofitHandler();
            retrofitHandler.getLoginRegisterAPI().loginToChat(loginEditText.getText().toString(),
                    passwordEditText.getText().toString(), new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {

                            if (s.contains("false")) {
                                Toast.makeText(getApplicationContext(), "You typed bad username or password", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String token = jsonObject.getString("token");
                                    Intent intent = new Intent(LoginActivity.this, TabBarActivity.class);
                                    intent.putExtra("TOKEN", token);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                        }
                    });

        }
    }


}
