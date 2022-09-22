package com.projetXML.meetups.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projetXML.meetups.Match;
import com.projetXML.meetups.ProfilUtilisateur;
import com.projetXML.meetups.R;
import com.projetXML.meetups.models.PublicUser;
import com.projetXML.meetups.viewHolders.MatchItemViewHolder;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchListAdapter extends RecyclerView.Adapter<MatchItemViewHolder> {
    private final List<PublicUser> list;
    private final Context context;

    public MatchListAdapter(Context context, List<PublicUser> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MatchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.match_list_item, parent, false);

        return new MatchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchItemViewHolder holder, int position) {
        PublicUser item = list.get(position);

        Picasso.get().load(item.getPhotoProfilURL()).into(holder.getImage());
        holder.getNom().setText(item.getPrenom() + " " + item.getNom());
        holder.getSexe().setText(item.getSexe());
        holder.getRoot().setOnClickListener(view -> {
            Intent i = new Intent(context, ProfilUtilisateur.class);
            i.putExtra("user", item);
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
