package com.projetXML.meetups.viewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projetXML.meetups.R;

public class ProfilePhotoViewHolder extends RecyclerView.ViewHolder {
    private final ImageView image;

    public ProfilePhotoViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image);
    }

    public ImageView getImage() {
        return image;
    }
}
