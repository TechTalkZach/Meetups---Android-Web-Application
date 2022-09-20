package com.projetXML.meetups.models;

public class LikeRequestBody {

    public int fromIdUser;
    public int toIdUser;
    public int liked;

    public LikeRequestBody(int fromIdUser, int toIdUser, int liked) {
        this.fromIdUser = fromIdUser;
        this.toIdUser = toIdUser;
        this.liked = liked;
    }
}
