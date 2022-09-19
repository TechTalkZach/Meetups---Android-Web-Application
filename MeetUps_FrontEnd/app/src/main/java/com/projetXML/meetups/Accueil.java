
package com.projetXML.meetups;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.state.AuthState;
import com.projetXML.meetups.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

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
                    System.out.println(">>>>>" +"---------------Hello");
                   Utilities.showAlert(getApplicationContext(), response.message());
                    //put back when API works -----    : return;
                }


                //list = response.body();
                list = new ArrayList<PublicUser>();
                list.add(
                        new PublicUser(1,"nom","prenom","sexe",35,160.5,"edu","situFami","relig","recherche","nullPic")
                );
                list.add(
                        new PublicUser(2,"nom2","prenom2","sexe2",35,160.52,"edu2","situFami2","relig2","recherche2","nullPic2")
                );
                updateUI();
                System.out.println(">>>>>" + list.size());
            }

            @Override
            public void onFailure(Call<List<PublicUser>> call, Throwable t) {
                System.out.println(">>>>>" +"---------------failure");
                Utilities.showAlert(getApplicationContext(), t.getMessage());
            }
        });
    }//getAvailableProfile()

    private void updateUI(){
        if(currentIndex >= list.size())
        {
            //getAvailableProfile();
            Utilities.showAlert(this, "Il n'y a aucune autre profil disponible pour le moment");
            return;

        }

        PublicUser publicUser = list.get(currentIndex);
        alertMsg(publicUser.toString());;


    }//updateUI()

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

    public void getDets(){
        //List<String> fields = entities.stream().map(YourEntity::publicUser()).collect(Collectors.toList());

      //  String listIter = list.iterator().toString();
       // System.out.println(listIter);

      //  PublicUser publicUserObj = (PublicUser) getIntent().getSerializableExtra("id");

       // System.out.println(publicUserObj);
        //alertMsg(listIter);

    }//getDets()

    private void btnClickEvents() {
        btnOui = findViewById(R.id.btnOui);
        btnNon = findViewById(R.id.btnNon);
        btnVoirDets = findViewById(R.id.btnDetails);

        btnOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO 1. Make a req to server if liked profile or not 2.UpdateUi which will load next person unto the screen.



                currentIndex++;
                updateUI();

                //alertMsg("Vous avez matcher avec ce profil!\nYou matched with this profile!");
                //getAvailableProfile();
              //Not working  System.out.println(getAvailableProfile().toString());
            }
        });

        btnNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertMsg("btnNon");

                //TODO 1. Make a req to server if liked profile or not 2.UpdateUi which will load next person unto the screen.


                currentIndex++;
                updateUI();

                //getAvailableProfile();
            }
        });

        btnVoirDets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alertMsg("btnVoirDets");

              getDets();
            }
        });

    }//btnClickEvents()



}
