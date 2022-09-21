package com.projetXML.meetups;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.projetXML.meetups.utilities.Utilities;

public class Match extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_utilisateur);

        Utilities.alertMsg(this,"You are on the Match page");

    }// OnCreate
}
