package com.projetXML.meetups;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.projetXML.meetups.adapters.MatchListAdapter;
import com.projetXML.meetups.api.RetrofitClient;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.state.AuthState;
import com.projetXML.meetups.utilities.Utilities;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Match extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatchListAdapter adapter;
    private List<PublicUser> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        recyclerView = findViewById(R.id.recyclerView);

        getMatch();


    }

    private void updateRecyclerView(){
        MatchListAdapter adapter = new MatchListAdapter(this,list);
        recyclerView.setAdapter(adapter);

    }

    private void getMatch(){
        Call<List<PublicUser>> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getMyMatch(AuthState.getMyID());

        call.enqueue(new Callback<List<PublicUser>>() {
            @Override
            public void onResponse(Call<List<PublicUser>> call, Response<List<PublicUser>> response) {
                if(response.code() != 200)
                    Utilities.alertMsg(getBaseContext(), response.message());
                else{
                    list = response.body();
                    updateRecyclerView();

                }
            }

            @Override
            public void onFailure(Call<List<PublicUser>> call, Throwable t) {
                System.out.println(t.getMessage());
                Utilities.alertMsg(getBaseContext(), "erreur lors de la requete");

            }
        });


    }

}
