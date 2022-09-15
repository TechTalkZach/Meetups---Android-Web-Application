package com.projetXML.meetups.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String Base_URL = "https://meetups01.herokuapp.com/";
    private static RetrofitClient mInstance;
    private static Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance(){

        if(mInstance == null){
            mInstance = new RetrofitClient();
        }

        return mInstance;
    }

    public API getAPI(){
        return retrofit.create(API.class);
    }




}//RetrofitClient
