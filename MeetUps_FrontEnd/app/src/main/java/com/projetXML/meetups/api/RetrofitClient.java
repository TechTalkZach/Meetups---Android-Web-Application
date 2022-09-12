package com.projetXML.meetups.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String Base_URL = "http://00..";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

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
