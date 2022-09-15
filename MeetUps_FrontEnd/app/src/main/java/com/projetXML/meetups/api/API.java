package com.projetXML.meetups.api;

import com.projetXML.meetups.models.LoginResponse;
import com.projetXML.meetups.models.PrivateUser;
import com.projetXML.meetups.models.RegisterRequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    //TEST
    @GET("/")
    Call<ResponseBody> connectToURL();

    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequestBody body);


    @POST("login")
    Call<LoginResponse> login(@Body PrivateUser body);


}// API
