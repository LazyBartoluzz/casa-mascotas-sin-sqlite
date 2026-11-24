package com.lostomates.mascotas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/** Adapter: relaciona DataSet y ViewHolder con el RecyclerView. */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaViewHolder> {
    public interface GustoListener { void onGusto(Mascota mascota); }
    private final List<Mascota> mascotas;
    private final boolean editable;
    private final GustoListener listener;

    public MascotaAdapter(List<Mascota> mascotas, boolean editable, GustoListener listener) {
        this.mascotas = mascotas;
        this.editable = editable;
        this.listener = listener;
    }

    @Override public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override public void onBindViewHolder(MascotaViewHolder holder, int position) {
        final Mascota item = mascotas.get(position);
        holder.foto.setImageResource(item.imagen);
        holder.nombre.setText(item.nombre);
        holder.contador.setText(String.valueOf(item.rating));
        holder.hueso.setImageResource(item.gustado ? R.drawable.ic_bone_yellow : R.drawable.ic_bone_outline);
        holder.hueso.setVisibility(editable ? View.VISIBLE : View.GONE);
        holder.hueso.setContentDescription((item.gustado ? "Quitar me gusta a " : "Me gusta ") + item.nombre);
        if (editable) {
            holder.hueso.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (listener != null) listener.onGusto(item);
                }
            });
        } else {
            holder.hueso.setOnClickListener(null);
        }
    }

    @Override public int getItemCount() { return mascotas.size(); }
}
