package com.projetXML.meetups.api;

import com.projetXML.meetups.models.LogInResponse;
import com.projetXML.meetups.models.PrivateUser;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.models.RegisterRequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    //TEST
    @GET("/")
    Call<ResponseBody> connectToURL();

    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequestBody body);

    @FormUrlEncoded
    @POST("userLogIn")
    Call<LogInResponse> userLogin(
            @Field("courriel") String courriel,
            @Field("motDePasse") String motDePasse
    );


}// API
