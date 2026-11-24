package com.lostomates.mascotas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/** Define la estructura visual de cada item del RecyclerView. */
public class MascotaViewHolder extends RecyclerView.ViewHolder {
    final ImageView foto;
    final TextView nombre;
    final TextView contador;
    final ImageButton hueso;

    public MascotaViewHolder(View itemView) {
        super(itemView);
        foto = (ImageView) itemView.findViewById(R.id.photo);
        nombre = (TextView) itemView.findViewById(R.id.name);
        contador = (TextView) itemView.findViewById(R.id.count);
        hueso = (ImageButton) itemView.findViewById(R.id.like);
    }
}
