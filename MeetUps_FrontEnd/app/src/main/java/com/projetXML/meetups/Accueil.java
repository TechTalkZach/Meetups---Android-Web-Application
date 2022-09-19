
package com.projetXML.meetups;

        import android.os.Bundle;

        import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD:MeetUps_FrontEnd/app/src/main/java/com/projetXML/meetups/Accueil.java
public class Accueil extends AppCompatActivity {
=======
import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.state.AuthState;
import com.projetXML.meetups.utilities.Utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
>>>>>>> c649f49ca43c0dd01d32bd009fdec7cb6e586e0c:MeetUps_FrontEnd/app/src/main/java/com/projetXML/meetups/HomePage.java

    List<PublicUser> list;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

<<<<<<< HEAD:MeetUps_FrontEnd/app/src/main/java/com/projetXML/meetups/Accueil.java
        //code for call to
=======
        getAvailableProfile();


>>>>>>> c649f49ca43c0dd01d32bd009fdec7cb6e586e0c:MeetUps_FrontEnd/app/src/main/java/com/projetXML/meetups/HomePage.java
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
                    Utilities.showAlert(getApplicationContext(), response.message());
                    return;
                }

                list = response.body();
                updateUI();
            }

            @Override
            public void onFailure(Call<List<PublicUser>> call, Throwable t) {
                Utilities.showAlert(getApplicationContext(), t.getMessage());
            }
        });
    }//getAvailableProfile

    private void updateUI(){
        if(currentIndex == list.size()){
            Utilities.showAlert(this, "Il n'y a aucune autre profil disponible pour le moment");
            return;
        }




    }

}
