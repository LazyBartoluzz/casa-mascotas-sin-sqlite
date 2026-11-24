package com.lostomates.mascotas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MascotasFragment extends Fragment {
    private RecyclerView recycler;
    private MascotaAdapter adapter;
    private FloatingActionButton fab;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_mascotas, container, false);
        recycler = (RecyclerView) root.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MascotaAdapter(MascotaRepository.get(getActivity()).todas(), true, new MascotaAdapter.GustoListener() {
            @Override public void onGusto(Mascota mascota) {
                MascotaRepository.get(getActivity()).cambiarGusto(mascota);
                adapter.notifyDataSetChanged();
                if (getActivity() instanceof MainActivity) ((MainActivity) getActivity()).refrescarFavoritos();
                Toast.makeText(getActivity(), mascota.gustado ? "Me gusta: " + mascota.nombre : "Se quitó el gusto: " + mascota.nombre, Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adapter);
        fab = (FloatingActionButton) root.findViewById(R.id.fab_subir);
        fab.hide();
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                if (rv.computeVerticalScrollOffset() > 250) fab.show(); else fab.hide();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { recycler.smoothScrollToPosition(0); }
        });
        return root;
    }

    public void refrescar() { if (adapter != null) adapter.notifyDataSetChanged(); }
}
