package com.projetXML.meetups.models;

public class RegisterRequestBody {

    public PrivateUser privateUser;
    public PublicUser publicUser;

    public RegisterRequestBody(PrivateUser privateUser, PublicUser publicUser){
        this.privateUser = privateUser;
        this.publicUser = publicUser;
    }
}
