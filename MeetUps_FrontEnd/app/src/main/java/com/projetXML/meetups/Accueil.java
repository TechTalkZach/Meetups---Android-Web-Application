
package com.projetXML.meetups;

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

import androidx.appcompat.app.AppCompatActivity;

import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.LikeRequestBody;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.state.AuthState;
import com.projetXML.meetups.utilities.Utilities;

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

                if(currentIndex >= list.size() || list.isEmpty()){

                    //Redirect to match page
                    Utilities.alertMsg(Accueil.this,"Il n'y a plus de profil disponible, vous serez redirigez à la page de match.\nThere is no more profiles.You will be redirected to the match page.");

                    //TEST---------------------------------------------------
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(Accueil.this, Match.class);
                            startActivity(i);
                        }
                    }, 5000);

                }
                else {
                    updateUI();
                }

                //When server down.
             /*   list = new ArrayList<PublicUser>();
                list.add(
                        new PublicUser(1,"nom","prenom","sexe",35,160.5,"edu","situFami","relig","recherche","nullPic")
                );
                list.add(
                        new PublicUser(2,"nom2","prenom2","sexe2",35,160.52,"edu2","situFami2","relig2","recherche2","nullPic2")
                );
                updateUI();
                System.out.println(">>>>>" + list.size());*/
            }
            @Override
            public void onFailure(Call<List<PublicUser>> call, Throwable t) {
                System.out.println(">>>>>" +"---------------failure");
                Utilities.alertMsg(getApplicationContext(), t.getMessage());
            }
        });
    }//getAvailableProfile()

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
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(">>>>>" +"---------------failure");
                System.out.println(t.getMessage());
            }
        });
    }//performLike()

    private void updateUI(){


        btnNon.setVisibility(View.VISIBLE);
        btnOui.setVisibility(View.VISIBLE);
        btnVoirDets.setVisibility(View.VISIBLE);


        if(currentIndex >= list.size() ||list.isEmpty()){

            //Redirect to match page
            Utilities.alertMsg(Accueil.this,"Il n'y a plus de profil disponible, vous serez redirigez à la page de match.\nThere is no more profiles.You will be redirected to the match page.");

            //TEST---------------------------------------------------
            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Accueil.this, Match.class);
                    startActivity(i);
                }
            }, 5000);

        }
        else {

            PublicUser publicUser = list.get(currentIndex);

            ImageView chgedImg = (ImageView) findViewById(R.id.imageId);

            System.out.println("===============================" + publicUser.getPhotoProfilURL());


            @SuppressLint("StaticFieldLeak")
            AsyncTask<String, Void, Bitmap> downloadTask = new AsyncTask<String, Void, Bitmap>() {


                @Override
                protected Bitmap doInBackground(String... strings) {
                    String urldisplay = publicUser.getPhotoProfilURL();
                    Bitmap img = null;
                    try {
                        InputStream in = new java.net.URL(urldisplay).openStream();
                        img = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        System.out.println("Error" + e.getMessage());
                        e.printStackTrace();
                    }
                    return img;
                }

                @Override
                protected void onPostExecute(Bitmap result) {
                    chgedImg.setImageBitmap(result);
                }
            };

            downloadTask.execute();
        }
    }//updateUI()


    public void getDets(){

        if(currentIndex >= list.size() || list.isEmpty() )
        {

                //Redirect to match page
                Utilities.alertMsg(Accueil.this,"Il n'y a plus de profil disponible, vous serez redirigez à la page de match.\nThere is no more profiles.You will be redirected to the match page.");

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(Accueil.this, Match.class);
                        startActivity(i);
                    }
                }, 5000);

            }
        else{
            PublicUser publicUser = list.get(currentIndex);

            Utilities.alertMsg(Accueil.this,"Nom: " +publicUser.getNom()+ "\nPrénom: " + publicUser.getPrenom() + "\nAge: " + publicUser.getAge());

        }

    }//getDets()



    private void btnClickEvents() {

        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performLike(1);

                currentIndex++;
                updateUI();

            }
        });

        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performLike(0);

                currentIndex++;
                updateUI();


                //getAvailableProfile();
            }
        });

        btnVoirDets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getDets();
            }
        });

    }//btnClickEvents()

}
