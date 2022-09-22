package com.projetXML.meetups.api;

import com.projetXML.meetups.models.LikeRequestBody;
import com.projetXML.meetups.models.LoginResponse;
import com.projetXML.meetups.models.Photo;
import com.projetXML.meetups.models.PrivateUser;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.models.RegisterRequestBody;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    //TEST
    @GET("/")
    Call<ResponseBody> connectToURL();

    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequestBody body);

    @POST("login")
    Call<LoginResponse> login(@Body PrivateUser body);

    @GET("availableprofile")
    Call<List<PublicUser>> getAvailableProfile(@Query("idUser") int idUser);

    @POST("likeProfile")
    Call<ResponseBody> likeProfile(@Body LikeRequestBody body);

    @GET("myMatch")
    Call<List<PublicUser>> getMyMatch(@Query("idUser") int idUser);

    @GET("photo")
    Call<List<Photo>> getPhotosForProfile(@Query("idUser") int idUser);


}// API
