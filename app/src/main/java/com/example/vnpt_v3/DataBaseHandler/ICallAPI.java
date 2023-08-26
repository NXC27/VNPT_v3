package com.example.vnpt_v3.DataBaseHandler;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ICallAPI {
    @FormUrlEncoded
    @GET("/login.php")
    Call<JSONObject> Login(
            @Field("email") String email,
            @Field("password") String password
    );
}
