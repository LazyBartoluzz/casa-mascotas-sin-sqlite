package com.lostomates.mascotas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.Holder> {
    private final int[] fotos;
    private final int[] conteos = {15, 12, 9, 7, 11, 8, 6, 10, 5};

    public FotoAdapter(int[] fotos) { this.fotos = fotos; }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int type) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        holder.photo.setImageResource(fotos[position]);
        holder.count.setText(String.valueOf(conteos[position % conteos.length]));
    }

    @Override public int getItemCount() { return fotos.length; }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView photo; TextView count;
        Holder(View view) {
            super(view);
            photo = (ImageView) view.findViewById(R.id.photo);
            count = (TextView) view.findViewById(R.id.count);
        }
    }
}
