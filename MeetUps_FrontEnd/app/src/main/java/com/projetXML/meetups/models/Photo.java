package com.projetXML.meetups.models;

public class Photo {

    private final int idPhoto;
    private final String photoURL;
    private final int publicUserId;


    public Photo(int idPhoto, String photoURL, int publicUserId) {
        this.idPhoto = idPhoto;
        this.photoURL = photoURL;
        this.publicUserId = publicUserId;
    }

    public String getPhotoURL(){ return photoURL; }

    public int getIdPhoto() {
        return idPhoto;
    }

    public int getPublicUserId() {
        return publicUserId;
    }
}
