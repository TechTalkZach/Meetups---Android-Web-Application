package com.projetXML.meetups.models;

import androidx.annotation.NonNull;

public class PublicUser {

        int id;
        String nom;
        String prenom;
        String sexe;
        int age;
        double grandeur;
        String education;
        String situationFamiliale;
        String religion;
        String recherche;
        String photoProfilURL;

    public PublicUser(int id, String nom, String prenom, String sexe, int age, double grandeur, String education, String situationFamiliale, String religion, String recherche, String photoProfilURL) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.grandeur = grandeur;
        this.education = education;
        this.situationFamiliale = situationFamiliale;
        this.religion = religion;
        this.recherche = recherche;
        this.photoProfilURL = photoProfilURL;
    }

    public PublicUser(String nom, String prenom, String sexe, int age, double grandeur, String education, String situationFamiliale, String religion, String recherche, String photoProfilURL) {
        this(-1, nom, prenom,sexe, age, grandeur, education, situationFamiliale, religion, recherche, photoProfilURL);
    }



    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }

    public double getGrandeur() {
        return grandeur;
    }

    public String getEducation() {
        return education;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public String getReligion() {
        return religion;
    }

    public String getRecherche() {
        return recherche;
    }

    @NonNull
    @Override
    public String toString() {
        return this.id + " "+ this.nom +" " + this.prenom + " " + this.age +" " + prenom +" " + nom +" " + prenom ;
    }
}
