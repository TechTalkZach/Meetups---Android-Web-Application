package com.projetXML.meetups.models;

public class LoginResponse {

   private int idUser;

   public LoginResponse(int idUser){
      this.idUser = idUser;
   }

   public int getIdUser(){ return idUser; }
}
