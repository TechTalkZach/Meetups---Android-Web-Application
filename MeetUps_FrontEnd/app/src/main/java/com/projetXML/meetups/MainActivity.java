package com.projetXML.meetups;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText userCourriel;
    EditText pass;
    Button btnCreerCompte;
    Button btnSeConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iLogIn();
        btnClick();

    }// OnCreate

    private void iLogIn() {
        userCourriel = findViewById(R.id.idCourriel);
        pass = findViewById(R.id.idMotDePass);
        btnCreerCompte = findViewById(R.id.idCreerCompte);
        btnSeConnecter = findViewById(R.id.idSeConnecter);
    }

    private void btnClick() {
        btnSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogInI();
            }
        });

        btnCreerCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreerCompte.class);
                startActivity(i);
            }
        });
    }//btnClick

    boolean validEmailI(EditText text) {
        CharSequence userCourriel = text.getText().toString();
        return (!TextUtils.isEmpty(userCourriel) && Patterns.EMAIL_ADDRESS.matcher(userCourriel).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence input = text.getText().toString();
        return TextUtils.isEmpty(input);
    }

    void checkLogInI() {
        boolean isValid = true;

        if (isEmpty(userCourriel)) {
            userCourriel.setError("Champ ne peut pas être vide. Field cannot be empty.");
            isValid = false;
        } else {
            if (!validEmailI(userCourriel)) {
                userCourriel.setError("Entrez un courriel valide. Enter a valid email!");
                isValid = false;
            }
        }

        if (isEmpty(pass)) {
            pass.setError("Champ ne peut pas être vide. Field cannot be empty.");
            isValid = false;
        } else {
            if (pass.getText().toString().length() < 5) {
                pass.setError("Mot de passe doit avoir au moins 5 caractères. Password must have at least 5 chars long");
                isValid = false;
            }
        }

        if (isValid) {
            String userNameI = userCourriel.getText().toString();
            String passI = pass.getText().toString();

            if (userNameI.equals("allo@allo.com") && passI.equals("pass1")) {
                /*
                Intent redirectPage = new Intent(MainActivity.this, HomePage.class);
                startActivity(redirectPage);
                this.finish();*/
            } else {
                //email & or pass not as above.

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Alerte");

                alertDialogBuilder.setMessage("Courriel ou Mot de pass invalide");
                alertDialogBuilder.setCancelable(false);


                alertDialogBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        }
    }// checkLogInI

}// MainActivity