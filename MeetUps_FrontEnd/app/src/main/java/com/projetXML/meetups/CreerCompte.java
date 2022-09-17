package com.projetXML.meetups;

import static java.lang.Character.getType;
import static java.lang.Integer.parseInt;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.PrivateUser;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.models.RegisterRequestBody;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreerCompte extends AppCompatActivity {

    Spinner spinnerEducation;
    Spinner spinnerReligion;

    Button btnSubmit;
    EditText idNom;
    EditText idPrenom;
    EditText idCourriel;
    EditText idAge;
    EditText idGrandeur;
    EditText idPass;
    RadioButton radioButtonHomme;
    RadioButton idRadioPasEnfantEtVeut;
    RadioButton idRadioPasEnfantEtNeVeutPas;
    RadioButton idRadioUnEnfantEtVeut;
    RadioButton idRadioRelationSerieuse;
    RadioButton idRadioUneRelation;
    RadioButton idRadioAmitieEtPlus;
    RadioButton idRadioADater;
    RadioButton idRadioPasDeBut;



    String emptyMsgErr = "Le champ ne peut pas être vide. The field cannot be empty.";
    String CompteCreerOkMsg = "Votre compte a bien été creer! Your account has been created!";
    String errSubmitMsg = "attention erreur(s). Attention error(s).";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        formI2();
        btnClick2();

        //<Spinner
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

        //Spinner>


    }// onCreate

    private void btnClick2() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formValidation();
            }
        });
    }//btnClick

     private void formI2() {
        idNom = findViewById(R.id.idNom);
        idPrenom = findViewById(R.id.idPrenom);
        idCourriel = findViewById(R.id.idCourriel);
        idAge = findViewById(R.id.idAge);
        idGrandeur = findViewById(R.id.idGrandeur);
        btnSubmit = findViewById(R.id.submit);
        idPass = findViewById(R.id.idMotDePass);
        radioButtonHomme = findViewById(R.id.idRadioHomme);
        idRadioPasEnfantEtVeut = findViewById(R.id.idRadioPasEnfantEtVeut);
        idRadioPasEnfantEtNeVeutPas = findViewById(R.id.idRadioPasEnfantEtNeVeutPas);
        idRadioUnEnfantEtVeut = findViewById(R.id.idRadioUnEnfantEtVeut);
        idRadioRelationSerieuse = findViewById(R.id.idRadioRelationSerieuse);
        idRadioUneRelation = findViewById(R.id.idRadioUneRelation);
         idRadioAmitieEtPlus = findViewById(R.id.idRadioAmitieEtPlus);
         idRadioADater = findViewById(R.id.idRadioADater);
         idRadioPasDeBut = findViewById(R.id.idRadioPasDeBut);


     }//formI

    private String getRadioBtnGrSexe(){
        if(radioButtonHomme.isChecked())
            return "H";
        else
            return "F";
    }//radioBtnGrSexe

    private String getRadioBtnSituationFamiliale(){
        if(idRadioPasEnfantEtVeut.isChecked()){
            return "N'a pas d'enfants et en veut";

        }
        else if(idRadioPasEnfantEtNeVeutPas.isChecked()){
            return "N'a pas d'enfants et n'en veut pas";

        }
        else if(idRadioUnEnfantEtVeut.isChecked()){
            return "A un ou plusieurs enfants et en veut plus";

        }
        else {
            return "A un ou plusiers enfants et n'en veut pas plus";
        }
    }//getRadioSituationFamiliale

    private String getMatchIdeale(){
        if(idRadioRelationSerieuse.isChecked())
            return "Une relation serieuse";

        if(idRadioUneRelation.isChecked())
            return "Une relation";

        if(idRadioAmitieEtPlus.isChecked())
            return "De l'amitie et qui sais peut-etre plus";

        if(idRadioADater.isChecked())
            return "A dater pour passer le temps";

        return "N'a pas de but precis";
    }



    private void registerUser(){
        //Inputs text toString()
        String nomStrI = idNom.getText().toString().trim();
        String prenomStrI = idPrenom.getText().toString().trim();
        String courielStrI = idCourriel.getText().toString().trim();
        String motDePasseStrI = idPass.getText().toString().trim();

        //Input Numbers toString()
        int ageStrI = parseInt(idAge.getText().toString());
        double grandeurDoubI = Double.parseDouble(idGrandeur.getText().toString().trim());

        //Input Spinners toString()
        String religionStrI = spinnerReligion.getSelectedItem().toString();
        String educationStrI = spinnerEducation.getSelectedItem().toString();

        //Input RadioButtons choice toString()
        String sexeStrI = getRadioBtnGrSexe();
        String situationFamilialeStrI = getRadioBtnSituationFamiliale();
        String rechercheStrI = getMatchIdeale();

        PrivateUser privateUser = new PrivateUser(courielStrI, motDePasseStrI);
        PublicUser publicUser = new PublicUser(nomStrI, prenomStrI, sexeStrI, ageStrI, grandeurDoubI, educationStrI, situationFamilialeStrI, religionStrI, rechercheStrI, null);
        RegisterRequestBody registerRequestBody = new RegisterRequestBody(privateUser, publicUser);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .register(registerRequestBody);

        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody>response){
                try {
                    if(response.code() == 200){
                        Toast.makeText(CreerCompte.this, response.message(), Toast.LENGTH_LONG).show();
                        finish();
                    } else{
                        alertMsg(response.message());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    alertMsg(e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CreerCompte.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
            });
    }//registerUser

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


    //Validation methods------------------------------------


    //Converting to string & kk if empty: true
    boolean isEmpty(EditText text) {
        CharSequence input = text.getText().toString().trim();
        return TextUtils.isEmpty(input);
    }

    boolean nomValide() {
        String nomStr = idNom.getText().toString().trim();

        // At least 2 caracters and up to 40, 2 or more letters, can be 0 or 1x -,', whitespace, if ' has to have a letter afterwards.
        String nomVal = "^(?=.{2,40}$)[a-zA-Z]{2,}(?:[-'\\s][a-zA-Z]+)*$";

        //TextInputLayout txtLayOutNom = (TextInputLayout) findViewById(R.id.idNom);


        if (isEmpty(idNom)) {
            idNom.setError(emptyMsgErr);
            return false;

        }
        else if (!nomStr.matches(nomVal)) {
            idNom.setError("Svp entrer un prénom valide. Please enter a valid name");
            return false;
        }
        else {
            // ok
            idNom.setError(null);
            //txtLayOutNom.setErrorEnabled(false);

            return true;
        }
    } //nomValide

    boolean prenomValide() {

        String prenomStr = idPrenom.getText().toString().trim();

        // At least 2 caracters and up to 40, 2 or more letters, can be 0 or 1x -,', whitespace, if ' has to have a letter afterwards.
        String prenomVal = "^(?=.{2,40}$)[a-zA-Z]{2,}(?:[-'\\s][a-zA-Z]+)*$";

        //TextInputLayout txtLayOutPrenom = (TextInputLayout) findViewById(R.id.idPrenom);

        if (isEmpty(idPrenom)) {
            idPrenom.setError(emptyMsgErr);
            return false;

        }
        else if (!prenomStr.matches(prenomVal)) {
            idPrenom.setError("Svp entrer un prénom valide. Please enter a valid name");
            return false;
        }
        else {
            // ok
            idPrenom.setError(null);
            //txtLayOutPrenom.setErrorEnabled(false);

            return true;
        }

    } //prenomValide


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

    boolean validAge() {

        if (isEmpty(idAge)) {
            idAge.setError(emptyMsgErr);
            return false;
        }

        int ageNbr = parseInt(idAge.getText().toString().trim());

        if ( ageNbr < 18 || ageNbr > 120){
            idAge.setError("Entrez un âge valide. Enter a valid age.");
            return false;
        }
        else {
            return true;
        }
    }

    boolean validGrandeur() {

        if (isEmpty(idGrandeur)) {
            idGrandeur.setError(emptyMsgErr);
            return false;
        }

        Double grandeurNbr = Double.parseDouble(idGrandeur.getText().toString().trim());

        if (grandeurNbr < 50.5 || grandeurNbr > 272.5){
            idGrandeur.setError("Entrez une grandeur valide. Enter a valid height.");
            return false;
        }
        else {
            return true;
        }
    }

    boolean validEdu() {

        if(spinnerEducation.getSelectedItemPosition() == 0 ) {

            ((TextView)spinnerEducation.getSelectedView()).setError("");
            return false;
        }
        else  {
            return true;
        }

    }//validEdu

    boolean validRelig() {
        if(spinnerReligion.getSelectedItemPosition() == 0  ){

            ((TextView)spinnerReligion.getSelectedView()).setError("Error message");
            return false;
        }
        else  {
            return true;
        }

    }//validRelig


    void formValidation() {

        if(nomValide() & prenomValide() & validEmailI() & validAge() & validGrandeur() & validEdu() & validRelig()){

            registerUser();
        }
        else{
            alertMsg(errSubmitMsg);
        }

    }// formCompletion

} //CreerCompte
