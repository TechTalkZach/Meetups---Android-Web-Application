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

    EditText idCourriel;
    EditText idPass;
    Button btnCreerCompte;
    Button btnSeConnecter;

    //String msgs
    String emptyMsgErr = "Le champ ne peut pas être vide. The field cannot be empty.";
    String errSubmitMsg = "attention erreur(s). Attention error(s).";
    String PageProfil = "ok redirect page aimer profil ou non";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formI();
        btnClick();

    }// OnCreate

    private void formI() {
        idCourriel = findViewById(R.id.idCourriel);
        idPass = findViewById(R.id.idMotDePass);
        btnCreerCompte = findViewById(R.id.idCreerCompteLogIn);
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

    public void alertMsg(String message){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Alerte");

        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);


        alertDialogBuilder.setNeutralButton("Ok",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                dialog.cancel();
            }
        });

        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }//alertMsg

    boolean isEmpty(EditText text) {
        CharSequence input = text.getText().toString();
        return TextUtils.isEmpty(input);
    }


    boolean validEmailI() {

        String courrielStr = idCourriel.getText().toString().trim();


        if (isEmpty(idCourriel)) {
            idCourriel.setError(emptyMsgErr);
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(courrielStr).matches()){
            idCourriel.setError("Entrez un courriel valide. Enter a valid email!");
            return false;
        }
        else {
            return true;
        }

    }//validEmailI


    boolean passValide(){
        String motDePasseStr = idPass.getText().toString().trim();

        // At least 5 caracters and up to 8 & least 1 number
        String passRegex ="^(?=.*\\d).{5,8}$";


        if (isEmpty(idPass)) {
            idPass.setError(emptyMsgErr);
            return false;
        }
        else if (!motDePasseStr.matches(passRegex)) {
            idPass.setError("Entrez un mot de passe valide. Enter a valid password.");
            return false;
        }
        else {
            return true;
        }

    }//passValide


    void checkLogInI() {

        if(validEmailI() & passValide()){

            //Redirection
            Intent redirectPage = new Intent(MainActivity.this, HomePage.class);
            startActivity(redirectPage);
            this.finish();

        }
        else{
            alertMsg(errSubmitMsg);
        }

    }// formCompletion

}// MainActivity