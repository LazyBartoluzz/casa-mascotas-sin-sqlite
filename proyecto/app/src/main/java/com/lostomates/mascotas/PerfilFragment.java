package com.lostomates.mascotas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PerfilFragment extends Fragment {
    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_perfil, parent, false);
        RecyclerView grid = (RecyclerView) root.findViewById(R.id.grid);
        grid.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        grid.setNestedScrollingEnabled(false);
        grid.setAdapter(new FotoAdapter(new int[] {
            R.drawable.gallery_1, R.drawable.gallery_2, R.drawable.gallery_3,
            R.drawable.gallery_4, R.drawable.gallery_5, R.drawable.gallery_6,
            R.drawable.gallery_7, R.drawable.gallery_8, R.drawable.gallery_9
        }));
        return root;
    }
}
