package com.projetXML.meetups.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.projetXML.meetups.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchItemViewHolder extends RecyclerView.ViewHolder {
    private final CircleImageView image;
    private final TextView nom;
    private final TextView sexe;
    private final ConstraintLayout root;

    public MatchItemViewHolder(View view){
        super(view);

        this.root = view.findViewById(R.id.root);
        this.image = view.findViewById(R.id.image);
        this.nom = view.findViewById(R.id.nom);
        this.sexe = view.findViewById(R.id.sexe);
    }

    public CircleImageView getImage() {
        return image;
    }

    public TextView getNom() {
        return nom;
    }

    public TextView getSexe() {
        return sexe;
    }

    public ConstraintLayout getRoot() {
        return root;
    }
}
