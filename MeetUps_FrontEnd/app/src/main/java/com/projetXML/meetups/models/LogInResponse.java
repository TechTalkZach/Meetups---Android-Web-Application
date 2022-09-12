package com.projetXML.meetups.models;

public class LogInResponse {

    private boolean error;
    private String mesg;
    private User user;

    public LogInResponse(boolean error, String mesg, User user) {
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

    public User getUser() {
        return user;
    }
}
