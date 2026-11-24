package com.lostomates.mascotas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FavoritosFragment extends Fragment {
    private RecyclerView recycler;
    private TextView empty;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_favoritos, parent, false);
        recycler = (RecyclerView) root.findViewById(R.id.recycler);
        empty = (TextView) root.findViewById(R.id.empty);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        refrescar();
        return root;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        recycler = null;
        empty = null;
    }

    public void refrescar() {
        if (recycler == null) return;
        java.util.List<Mascota> items = MascotaRepository.get(getActivity()).favoritas();
        recycler.setAdapter(new MascotaAdapter(items, false, null));
        empty.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
