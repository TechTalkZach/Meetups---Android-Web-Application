package com.projetXML.meetups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.LoginResponse;
import com.projetXML.meetups.models.PrivateUser;
import com.projetXML.meetups.state.AuthState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    EditText userCourriel;
    EditText pass;
    Button btnCreerCompte;
    Button btnSeConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        btnClick();

    }// OnCreate

    private void findViewByIds() {
        userCourriel = findViewById(R.id.idCourriel);
        pass = findViewById(R.id.idMotDePass);
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

        String motDePasseStr = pass.getText().toString().trim();

        // At least 5 caracters and up to 8 & least 1 number
        String passRegex ="^(?=.*\\d).{5,8}$";

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
            if (!motDePasseStr.matches(passRegex)) {
                pass.setError("Entrez un mot de passe valide. Enter a valid password.");

                //Toast error
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toastErr = Toast.makeText(context, "Min 5 car. à 8 max et au moins un chiffres.",duration);
                toastErr.show();
                isValid = false;
            }
            else{
                isValid = true;
            }
        }

        if (isValid) {
            login();
        }
    }// checkLogInI

    private void navigateToHomePage(){
        Intent intent = new Intent(this, Accueil.class);
        startActivity(intent);
    }


    private void login(){
        String userNameI = userCourriel.getText().toString().trim();
        String passI = pass.getText().toString().trim();
        PrivateUser privateUser = new PrivateUser(userNameI, passI);

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getAPI()
                .login(privateUser);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.code() == 200){
                    //Enregistrer le idUser dans authState
                    AuthState.setMyID(response.body().getIdUser());

                    //Redirect to home page
                    navigateToHomePage();

                } else {
                    System.out.println(response.message());
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;

                    Toast toastErr = Toast.makeText(context, response.message(),duration);
                    toastErr.show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t.getMessage());

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toastErr = Toast.makeText(context, t.getMessage(),duration);
                toastErr.show();
            }
        });

    }//logIn()

}// MainActivity