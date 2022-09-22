
package com.projetXML.meetups;

import android.content.DialogInterface;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.LikeRequestBody;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.state.AuthState;
import com.projetXML.meetups.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Accueil extends AppCompatActivity {

    //Buttons
    Button btnOui;
    Button btnNon;
    Button btnVoirDets;
    Button btnMatch;

    ImageView chgedImg;


    //Liste d'objets PublicUser
    List<PublicUser> list;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        //findViewById's
        btnOui = findViewById(R.id.btnOui);
        btnNon = findViewById(R.id.btnNon);
        btnVoirDets = findViewById(R.id.btnDetails);
        btnMatch = findViewById(R.id.btnMatchs);
        chgedImg = findViewById(R.id.imageId);

        //setVisibility
        btnNon.setVisibility(View.GONE);
        btnOui.setVisibility(View.GONE);
        btnVoirDets.setVisibility(View.GONE);

        //Displays first available profile & enables clickEvents
        getAvailableProfile();
        btnClickEvents();

    }// OnCreate

    //Function to get all available profile on the server
    private void getAvailableProfile(){
        Call<List<PublicUser>> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getAvailableProfile(AuthState.getMyID());

        call.enqueue(new Callback<List<PublicUser>>() {
            @Override
            public void onResponse(Call<List<PublicUser>> call, Response<List<PublicUser>> response) {
                if(response.code() != 200){

                    System.out.println(">>>>>" +"---------------not 200");
                   Utilities.alertMsg(Accueil.this,response.message());

                    return;
                }

                list = response.body();
                updateUI();

            }
            @Override
            public void onFailure(Call<List<PublicUser>> call, Throwable t) {
                System.out.println(">>>>>" +"---------------failure");
                Utilities.alertMsg(Accueil.this, t.getMessage());
            }
        });
    }//getAvailableProfile()


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





    private void performLike(int liked) {
        PublicUser user= list.get(currentIndex);

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .likeProfile(new LikeRequestBody(AuthState.getMyID(),user.getId(),liked));

        call.enqueue(new Callback <ResponseBody> () {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() != 200){

                    System.out.println(">>>>>" +"---------------not 200");
                    System.out.println(response.message());

                    return;
                }
                System.out.println(response.message());
                currentIndex++;
                updateUI();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(">>>>>" +"---------------failure");
                System.out.println(t.getMessage());
                currentIndex++;
                updateUI();
            }
        });
    }//performLike()

    private void updateUI(){


        btnNon.setVisibility(View.VISIBLE);
        btnOui.setVisibility(View.VISIBLE);
        btnVoirDets.setVisibility(View.VISIBLE);



        if(currentIndex >= list.size() ||list.isEmpty()){

            //Redirect to match page
            Utilities.alertMsg(Accueil.this,"Il n'y a plus de profil disponible");
            btnNon.setVisibility(View.GONE);
            btnOui.setVisibility(View.GONE);
            btnVoirDets.setVisibility(View.GONE);


        }
        else {

            PublicUser publicUser = list.get(currentIndex);
            Picasso
                    .get()
                    .load(publicUser.getPhotoProfilURL())
                    .into(chgedImg);
        }
    }//updateUI()


    public void getDets(){

        if(currentIndex >= list.size() || list.isEmpty() )
        {
            //Redirect to match page
            Utilities.alertMsg(Accueil.this,"Il n'y a plus de profil disponible");
        }
        else{
            PublicUser publicUser = list.get(currentIndex);
            Utilities.alertMsg(Accueil.this,"Nom: " +publicUser.getNom()+ "\nPrénom: " + publicUser.getPrenom() + "\nAge: " + publicUser.getAge());
        }

    }//getDets()



    private void btnClickEvents() {

        btnOui.setOnClickListener(view -> performLike(1));

        btnNon.setOnClickListener(view -> performLike(0));

        btnVoirDets.setOnClickListener(view -> getDets());

        btnMatch.setOnClickListener(view -> {
            Intent intent = new Intent(this, Match.class);
            startActivity(intent);
        });

    }//btnClickEvents()

}
