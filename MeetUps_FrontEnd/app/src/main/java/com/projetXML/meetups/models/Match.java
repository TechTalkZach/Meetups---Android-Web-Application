package com.projetXML.meetups.models;

public class Match {

    private int fromIdUser;
    private int toIdUser;
    private boolean liked;

    public Match(int fromIdUser, int toIdUser, boolean liked){
        this.fromIdUser = fromIdUser;
        this.toIdUser = toIdUser;
        this.liked = liked;
    }
}
