package com.projetXML.meetups;

import static java.lang.Integer.parseInt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.PrivateUser;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.models.RegisterRequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreerCompte extends AppCompatActivity {

    //Spinners inputs
    Spinner spinnerEducation;
    Spinner spinnerReligion;

    //Edit texts inputs
    EditText nomFv;
    EditText prenomFv;
    EditText courrielFv;
    EditText passFv;
    EditText ageFv;
    EditText grandeurFv;

    //Button
    Button btnSubmitFv;

    //sexe radioButtons
    RadioButton radioButtonHFv;
    RadioButton radioButtonFFv;

    //Situation familiale radioButtons
    RadioButton radioPasEnfantEtVeutFv;
    RadioButton radioPasEnfantEtNeVeutPasPlusFv;
    RadioButton radioUnEnfantOuPlusEtVeutPlusFv;
    RadioButton radioUnEnfantOuPlusEtNeVeutPasPlusFv;

    //Recherche radioButtons
    RadioButton radioRelationSerieuseFv;
    RadioButton radioUneRelationFv;
    RadioButton radioAmitieEtPlusFv;
    RadioButton radioADaterFv;
    RadioButton radioPasDeButFv;

    //Strings messages
    String emptyMsgErr = "Le champ ne peut pas être vide. The field cannot be empty.";
    String CompteCreerOkMsg = "Votre compte a bien été creer! Your account has been created!";
    String errSubmitMsg = "attention erreur(s). Attention error(s).";

    String errValidity= "Svp faire une saisie valide. Please enter a valid input";

    //RadioButtons exceptions
    String errExcNullRadioButtonChoice ="Ni l'un ni l'autre des buttons radios a été choisi.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        spinnersChoiceDisplays();
        findViewsByIds();
        setBtnSubmit();


    }// onCreate

    private void spinnersChoiceDisplays() {

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

    }//btnSpinners()

    private void setBtnSubmit() {
        btnSubmitFv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    formValidation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }//setBtnSubmit()

     private void findViewsByIds() {

        //Texts inputs findViewByIds
        nomFv = findViewById(R.id.idNom);
        prenomFv = findViewById(R.id.idPrenom);
        courrielFv = findViewById(R.id.idCourriel);
        passFv = findViewById(R.id.idMotDePass);
        ageFv = findViewById(R.id.idAge);
        grandeurFv = findViewById(R.id.idGrandeur);

        //Button findViewByIds
        btnSubmitFv = findViewById(R.id.submit);


        //-----------RadioButtons findViewByIds-----------

        //sexe findViewByIds
        radioButtonHFv = findViewById(R.id.idRadioHomme);
        radioButtonFFv = findViewById(R.id.idRadioFemme);

        //Situation familiale findViewByIds
        radioPasEnfantEtVeutFv = findViewById(R.id.idRadioPasEnfantEtVeut);
        radioPasEnfantEtNeVeutPasPlusFv = findViewById(R.id.idRadioPasEnfantEtNeVeutPas);
        radioUnEnfantOuPlusEtVeutPlusFv = findViewById(R.id.idRadioUnEnfantEtVeut);
        radioUnEnfantOuPlusEtNeVeutPasPlusFv = findViewById(R.id.idRadioUnEnfantEtNeVeutPas);

        //Recherche findViewByIds
        radioRelationSerieuseFv = findViewById(R.id.idRadioRelationSerieuse);
        radioUneRelationFv = findViewById(R.id.idRadioUneRelation);
        radioAmitieEtPlusFv = findViewById(R.id.idRadioAmitieEtPlus);
        radioADaterFv = findViewById(R.id.idRadioADater);
        radioPasDeButFv = findViewById(R.id.idRadioPasDeBut);
        
     }//findViewByIds()

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
    }//alertMsg()


    private String getRadioBtnSexe() throws Exception{

        if(radioButtonHFv.isChecked()){
            return "H";
        }
        else if(radioButtonFFv.isChecked()){
            return "F";
        }
        else{
            throw new Exception(errExcNullRadioButtonChoice);
        }

    }//getRadioBtnSexe()

    private String getRadioBtnSituationFamiliale() throws Exception {

        if (radioPasEnfantEtVeutFv.isChecked()) {
            return "N'a pas d'enfants et en veut";

        } else if (radioPasEnfantEtNeVeutPasPlusFv.isChecked()) {
            return "N'a pas d'enfants et n'en veut pas";

        } else if (radioUnEnfantOuPlusEtVeutPlusFv.isChecked()) {
            return "A un ou plusieurs enfants et en veut plus";

        } else if (radioUnEnfantOuPlusEtNeVeutPasPlusFv.isChecked()) {
            return "A un ou plusiers enfants et n'en veut pas plus";
        }
        else{
            throw new Exception(errExcNullRadioButtonChoice);
        }

    }//getRadioBtnSituationFamiliale()

    private String getMatchIdealeAkaRecherche() throws Exception{
        if(radioRelationSerieuseFv.isChecked())
        {
            return "Une relation serieuse";
        }

        else if(radioUneRelationFv.isChecked())
        {
            return "Une relation";
        }

        else if(radioAmitieEtPlusFv.isChecked())
        {
            return "De l'amitie et qui sais peut-etre plus";
        }

        else if(radioADaterFv.isChecked())
        {
            return "A dater pour passer le temps";
        }

        else if(radioPasDeButFv.isChecked())
        {
            return "N'a pas de but precis";
        }
        else{
            throw new Exception(errExcNullRadioButtonChoice);
        }

    }//getMatchIdealeAkaRecherche()

    private void registerUser() throws Exception {
        //Inputs text toString()
        String nomStrI = nomFv.getText().toString().trim();
        String prenomStrI = prenomFv.getText().toString().trim();
        String courielStrI = courrielFv.getText().toString().trim();
        String motDePasseStrI = passFv.getText().toString().trim();

        //Input Numbers toString()
        int ageStrI = parseInt(ageFv.getText().toString());
        double grandeurDoubI = Double.parseDouble(grandeurFv.getText().toString().trim());

        //Input Spinners toString()
        String religionStrI = spinnerReligion.getSelectedItem().toString();
        String educationStrI = spinnerEducation.getSelectedItem().toString();

        //Input RadioButtons choice toString()
        String sexeStr = getRadioBtnSexe();
        String situationFamilialeStr = getRadioBtnSituationFamiliale();
        String matchIdealeAkaRechercheStr = getMatchIdealeAkaRecherche();

        PrivateUser privateUser = new PrivateUser(courielStrI, motDePasseStrI);
        PublicUser publicUser = new PublicUser(nomStrI, prenomStrI, sexeStr, ageStrI, grandeurDoubI, educationStrI, situationFamilialeStr, religionStrI, matchIdealeAkaRechercheStr, null);
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


    //------------------------------------Validation methods------------------------------------

    //Converting to string & kk if empty: true
    boolean isEmpty(EditText text) {
        CharSequence input = text.getText().toString().trim();

        //returns true or false
        return TextUtils.isEmpty(input);
    }//isEmpty()

    boolean nomValide() throws Exception {
        String nomStr = nomFv.getText().toString().trim();

        // At least 2 caracters and up to 40, 2 or more letters, can be 0 or 1x -,', if has ' ,then has to have a letter afterwards.
        String regexNom = "^(?=.{2,40}$)[a-zA-Z]{2,}(?:[-'\\s][a-zA-Z]+)*$";

        if((!isEmpty(nomFv)) & (nomStr.matches(regexNom))){

            //OK
            nomFv.setError(null);

            //txtLayOutNom.setErrorEnabled(false);

            return true;
        }

        else if((isEmpty(nomFv)) & (!nomStr.matches(regexNom))){

            nomFv.setError(emptyMsgErr + " " + errValidity);

            //txtLayOutNom.setErrorEnabled(false);

            return false;

        }
        else if((isEmpty(nomFv)) || (!nomStr.matches(regexNom))){

            if((isEmpty(nomFv))){
                nomFv.setError(emptyMsgErr);
                return false;
            }
            else if((!nomStr.matches(regexNom))){
                nomFv.setError(errValidity);
                return false;
            }
            return false;
        }
        else {
            throw new Exception("Error on nom field");
        }

    } //nomValide

    boolean prenomValide() throws Exception {

        String prenomStr = prenomFv.getText().toString().trim();

        // At least 2 caracters and up to 40, 2 or more letters, can be 0 or 1x -,', whitespace, if ' has to have a letter afterwards.
        String regexPrenom = "^(?=.{2,40}$)[a-zA-Z]{2,}(?:[-'\\s][a-zA-Z]+)*$";

        if((!isEmpty(prenomFv)) & (prenomStr.matches(regexPrenom))){

            //OK
            nomFv.setError(null);

            //txtLayOutNom.setErrorEnabled(false);

            return true;
        }

        else if((isEmpty(prenomFv)) & (!prenomStr.matches(regexPrenom))){

            prenomFv.setError(emptyMsgErr + " " + errValidity);

            //txtLayOutNom.setErrorEnabled(false);

            return false;

        }
        else if((isEmpty(prenomFv)) || (!prenomStr.matches(regexPrenom))){

            if((isEmpty(prenomFv))){
                prenomFv.setError(emptyMsgErr);
                return false;
            }
            else if((!prenomStr.matches(regexPrenom))){
                prenomFv.setError(errValidity);
                return false;
            }
            return false;
        }
        else {
            throw new Exception("Error on prénom field");
        }


    } //prenomValide


    boolean validEmailI() {

        String courrielStr = courrielFv.getText().toString().trim();


        if (isEmpty(courrielFv)) {
            courrielFv.setError(emptyMsgErr);
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(courrielStr).matches()){
                courrielFv.setError("Entrez un courriel valide. Enter a valid email!");
                return false;
            }
        else {
            return true;
        }

    }//validEmailI

    boolean passValide(){
        String motDePasseStr = passFv.getText().toString().trim();

        // At least 5 caracters and up to 8 & least 1 number
        String passRegex ="^(?=.*\\d).{5,8}$";


        if (isEmpty(passFv)) {
            passFv.setError(emptyMsgErr);
            return false;
        }
        else if (!motDePasseStr.matches(passRegex)) {
            passFv.setError("Entrez un mot de passe valide. Enter a valid password.");
            return false;
        }
        else {
            return true;
        }

    }//passValide

    boolean validAge() {

        if (isEmpty(ageFv)) {
            ageFv.setError(emptyMsgErr);
            return false;
        }

        int ageNbr = parseInt(ageFv.getText().toString().trim());

        if ( ageNbr < 18 || ageNbr > 120){
            ageFv.setError("Entrez un âge valide. Enter a valid age.");
            return false;
        }
        else {
            return true;
        }
    }

    boolean validGrandeur() {

        if (isEmpty(grandeurFv)) {
            grandeurFv.setError(emptyMsgErr);
            return false;
        }

        Double grandeurNbr = Double.parseDouble(grandeurFv.getText().toString().trim());

        if (grandeurNbr < 50.5 || grandeurNbr > 272.5){
            grandeurFv.setError("Entrez une grandeur valide. Enter a valid height.");
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


    void formValidation() throws Exception {

        //All need to be executed & true, but in order to display every error msg at the same time if needed all need to be checked
        if(nomValide() & prenomValide() & validEmailI() & passValide() & validAge() & validGrandeur() & validEdu() & validRelig()){

            registerUser();
            alertMsg(CompteCreerOkMsg);

            Intent i = new Intent(CreerCompte.this, MainActivity.class);
            startActivity(i);
        }
        else{
            alertMsg(errSubmitMsg);
        }

    }// formCompletion

} //CreerCompte
