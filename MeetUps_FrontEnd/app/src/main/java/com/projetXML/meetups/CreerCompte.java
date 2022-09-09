package com.projetXML.meetups;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CreerCompte extends AppCompatActivity {

    Spinner spinnerEducation;
    Spinner spinnerReligion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        spinnerEducation = findViewById(R.id.idSpinnerEducation);

        String[] education = getResources().getStringArray(R.array.education);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, education);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEducation.setAdapter(adapter1);

        spinnerReligion = findViewById(R.id.idSpinnerReligion);

        String[] religion = getResources().getStringArray(R.array.religion);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, religion);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReligion.setAdapter(adapter2);

    }
}
