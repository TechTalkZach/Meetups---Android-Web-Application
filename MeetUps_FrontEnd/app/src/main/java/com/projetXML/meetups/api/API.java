package com.projetXML.meetups.api;

import com.projetXML.meetups.models.LogInResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("createUser")
    Call<ResponseBody> createUser(
        //@Field("id") int id,
        @Field("nom") String nom,
        @Field("prenom") String prenom,
        @Field("sexe") String sexe,
        @Field("age") int age,
        @Field("grandeur")double grandeur,
        @Field("education") String education,
        @Field("situationFamiliale") String situationFamiliale,
        @Field("religion") String religion
        //@Field("recherche") String recherche

    );

    @FormUrlEncoded
    @POST("userLogIn")
    Call<LogInResponse> userLogin(
            @Field("courriel") String courriel,
            @Field("motDePasse") String motDePasse
    );


}// API
