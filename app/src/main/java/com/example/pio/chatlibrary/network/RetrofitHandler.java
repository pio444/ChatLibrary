package com.example.pio.chatlibrary.network;


import android.os.Handler;

import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.RestAdapter;
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


    public RetrofitHandler() {
        initRetrofit();

    }

    private void initRetrofit() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(LoginRegisterAPI.BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL).
                setConverter(new StringConverter()).build();
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
