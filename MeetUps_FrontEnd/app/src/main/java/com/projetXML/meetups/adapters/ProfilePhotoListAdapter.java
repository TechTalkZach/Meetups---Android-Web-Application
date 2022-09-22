package com.projetXML.meetups.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projetXML.meetups.R;
import com.projetXML.meetups.models.Photo;
import com.projetXML.meetups.viewHolders.ProfilePhotoViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfilePhotoListAdapter extends RecyclerView.Adapter<ProfilePhotoViewHolder> {
    private final List<Photo> list;

    public ProfilePhotoListAdapter(List<Photo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ProfilePhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.profile_photo_list_item, parent, false);

        return new ProfilePhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePhotoViewHolder holder, int position) {
        Photo item = list.get(position);

        Picasso
                .get()
                .load(item.getPhotoURL())
                .into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
