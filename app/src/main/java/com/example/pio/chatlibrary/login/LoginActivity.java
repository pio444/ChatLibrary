package com.example.pio.chatlibrary.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pio.chatlibrary.MainActivity;
import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.TabBarActivity;
import com.example.pio.chatlibrary.network.Retrofit;
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

    public static final String TAG = LoginActivity.class.getSimpleName();

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button)findViewById(R.id.button_login);
        Animation animationLeft = AnimationUtils.loadAnimation(this, R.anim.animation_left);
        buttonLogin.setAnimation(animationLeft);

        loginEditText = (EditText) findViewById(R.id.editTextUserName);
        loginEditText.setText("czesiek123");
        passwordEditText = (EditText) findViewById(R.id.editTextUserPassword);
        passwordEditText.setText("Zaq!12wsx");

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


}