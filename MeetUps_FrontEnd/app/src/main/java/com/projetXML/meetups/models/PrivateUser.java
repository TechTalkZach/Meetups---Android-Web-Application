package com.projetXML.meetups.models;

public class PrivateUser {

    private int idUser;
    private String courriel;
    private String motDePasse;


    public PrivateUser(int idUser, String courriel, String motDePasse){
        this.idUser = idUser;
        this.courriel = courriel;
        this.motDePasse = motDePasse;
    }

    public PrivateUser(String courriel, String motDePasse){
        this(-1, courriel, motDePasse);
    }
}
