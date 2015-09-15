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
        loginEditText.setText("normalny");
        passwordEditText = (EditText) findViewById(R.id.editTextUserPassword);
        passwordEditText.setText("Dupek123!");

    }

    public void singIn(View view) {

        if (!Validation.validationName(loginEditText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid login", Toast.LENGTH_SHORT).show();
        } else if (!Validation.validationPassword(passwordEditText.getText().toString(), passwordEditText.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
        } else {
            RetrofitHandler retrofitHandler = new RetrofitHandler(getApplicationContext(), getResources().getString(R.string.signin));
            retrofitHandler.getLoginRegisterAPI().loginToChat(loginEditText.getText().toString(),
                    passwordEditText.getText().toString(), new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String token = jsonObject.getString("token");
                                Intent intent = new Intent(LoginActivity.this, TabBarActivity.class);
                                intent.putExtra("TOKEN", token);
                                intent.putExtra("LOGIN", loginEditText.getText().toString());
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getApplicationContext(), "Something goes wrong, check your connection or typed username and password", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }


}
