package com.example.pio.chatlibrary.network;


import android.content.Context;
import android.os.Handler;

import com.example.pio.chatlibrary.R;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by pio on 10.09.15.
 */
public class RetrofitHandler  {


    private LoginRegisterAPI loginRegisterAPI;
    private Context context;


    public RetrofitHandler(Context context, String type) {
        this.context = context;
        initRetrofit(type);

    }

    private void initRetrofit(String type) {
        RestAdapter restAdapter = null;
        if (type.equals(context.getResources().getString(R.string.signin))) {
            restAdapter = new RestAdapter.Builder().
                    setEndpoint(LoginRegisterAPI.BASE_URL).
                    setConverter(new StringConverter()).
                    setLogLevel(RestAdapter.LogLevel.FULL).
                    setLog(new AndroidLog("YOUR_LOG_TAG")).
                    build();
        }
        else if (type.equals(context.getResources().getString(R.string.register))) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(LoginRegisterAPI.BASE_URL).
                    setLogLevel(RestAdapter.LogLevel.FULL).
                    setLog(new AndroidLog("YOUR_LOG_TAG")).
                    build();
        }
        loginRegisterAPI = restAdapter.create(LoginRegisterAPI.class);

    }

    public LoginRegisterAPI getLoginRegisterAPI(){
        return loginRegisterAPI;
    }

    static class StringConverter implements Converter {

        @Override
        public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
            String text = null;
            try {
                text = fromStream(typedInput.in());
            } catch (IOException ignored) {/*NOP*/ }

            return text;
        }

        @Override
        public TypedOutput toBody(Object o) {
            return null;
        }

        public static String fromStream(InputStream in) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }
            return out.toString();
        }
    }


}
