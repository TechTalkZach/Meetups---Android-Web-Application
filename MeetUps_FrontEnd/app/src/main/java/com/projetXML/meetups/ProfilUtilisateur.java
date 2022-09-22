package com.projetXML.meetups;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.projetXML.meetups.adapters.ProfilePhotoListAdapter;
import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.Photo;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.state.AuthState;
import com.projetXML.meetups.utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilUtilisateur extends AppCompatActivity {

    PublicUser user;
    CircleImageView image;
    TextView recherche;
    TextView nom;
    RecyclerView recyclerView;
    List<Photo> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_utilisateur);

        image = findViewById(R.id.circleImageView);
        recherche = findViewById(R.id.textView);
        nom = findViewById(R.id.nom);
        recyclerView = findViewById(R.id.recyclerView);
        user = (PublicUser) getIntent().getSerializableExtra("user");

        updateUI();
        getPhotoForProfile();
    }

    private void getPhotoForProfile(){
        Call<List<Photo>> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getPhotosForProfile(user.getId());

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.code() != 200){
                    Utilities.showAlert(getApplicationContext(), response.message());
                } else{
                    list = response.body();
                    updateRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("tag", t.getMessage());
            }
        });
    }

    private void updateRecyclerView(){
        ProfilePhotoListAdapter adapter = new ProfilePhotoListAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void updateUI(){

        Picasso.get().load(user.getPhotoProfilURL()).into(image);
        nom.setText(user.getPrenom() + " " + user.getNom());
        recherche.setText(user.getRecherche());
    }
}
