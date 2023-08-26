package com.example.vnpt_v3.DataBaseHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String url = "https://vnpt-web-api.000webhostapp.com"; //Dia chi url call api
    private static Retrofit retrofitClient;
    private static final Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getInstance()
    {
        if (retrofitClient == null)
        {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitClient;
    }

    public static void setUrl(String url) {
        RetrofitClient.url = url;
    }

}
