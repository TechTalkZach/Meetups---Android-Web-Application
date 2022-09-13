package com.projetXML.meetups.models;

import retrofit2.http.Field;

public class User {

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

    public User(int id, String nom, String prenom, String sexe, int age, double grandeur, String education, String situationFamiliale, String religion, String recherche) {
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
}
