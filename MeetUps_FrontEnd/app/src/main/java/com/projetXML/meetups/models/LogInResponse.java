package com.projetXML.meetups.models;

public class LogInResponse {

    private boolean error;
    private String mesg;
    private PublicUser user;

    public LogInResponse(boolean error, String mesg, PublicUser user) {
        this.error = error;
        this.mesg = mesg;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMesg() {
        return mesg;
    }

    public PublicUser getUser() {
        return user;
    }
}
