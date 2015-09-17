package com.example.pio.chatlibrary.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.pio.chatlibrary.R;
import com.example.pio.chatlibrary.TabBarActivity;
import com.example.pio.chatlibrary.login.LoginActivity;
import com.example.pio.chatlibrary.login.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by bober on 17.09.15.
 */
public class Retrofit {

    public static final String TAG = Retrofit.class.getSimpleName();
    private Context applicationContext;
    private Activity activity;

    public Retrofit(Context applicationContext, Activity activity) {
        this.applicationContext = applicationContext;
        this.activity = activity;
    }

    public void sign_up(ModelRegister model, final Handler mHandler) {
        RetrofitHandler retrofit = new RetrofitHandler(applicationContext, applicationContext.getResources().getString(R.string.register));
        retrofit.getLoginRegisterAPI().sign_up(model, new Callback<ModelRegister>() {
            @Override
            public void success(ModelRegister modelRegister, Response response) {
                Log.d(TAG + "/retrofit", response.getUrl());
                Log.d(TAG + "/retrofit", String.valueOf(response.getStatus()));
                Log.d(TAG + "/retrofit", String.valueOf(response.getStatus()));
                Intent intent = new Intent(applicationContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                applicationContext.startActivity(intent);
                activity.finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG + "/retrofit", error.toString());
                if (error.toString().matches(".*failed to connect.*")) {
                    Message message = mHandler.obtainMessage(RegisterActivity.BACKGROUND_OPERATION);
                    Bundle bundle = new Bundle();
                    bundle.putString("message", applicationContext.getResources().getString(R.string.failed_to_connect));
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                }
                else {
                    String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                    Log.e("failure", json.toString());
                    String msg = json.toString().replaceFirst("\\{\"errors\":\\[\"", "");
                    msg = msg.replaceFirst("\"\\]\\}", "");
                    msg = msg.replaceFirst("\",\"", ", ");
                    Message message = mHandler.obtainMessage(RegisterActivity.BACKGROUND_OPERATION);
                    Bundle bundle = new Bundle();
                    bundle.putString("message", msg);
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                }

            }
        });
    }

    public void sign_in(final String login, String password) {
        RetrofitHandler retrofitHandler = new RetrofitHandler(applicationContext, applicationContext.getResources().getString(R.string.signin));
        retrofitHandler.getLoginRegisterAPI().loginToChat(login,
                password, new Callback<String>() {
                    @Override
                    public void success(String s, Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String token = jsonObject.getString("token");
                            Intent intent = new Intent(applicationContext, TabBarActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("TOKEN", token);
                            intent.putExtra("LOGIN", login);
                            applicationContext.startActivity(intent);
                            activity.finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(applicationContext, "Something goes wrong, check your connection or typed username and password", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void sign_out(String authorization) {
        RetrofitHandler retrofit = new RetrofitHandler(applicationContext, applicationContext.getResources().getString(R.string.register));
        retrofit.getLoginRegisterAPI().sign_out(authorization, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, String.valueOf(response.getStatus()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
            }
        });
    }
}
