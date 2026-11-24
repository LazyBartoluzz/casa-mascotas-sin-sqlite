package com.lostomates.mascotas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/** Tres Fragments gestionados por un ViewPager de la biblioteca support-v4. */
public class MascotasPagerAdapter extends FragmentPagerAdapter {
    private FavoritosFragment favoritos;

    public MascotasPagerAdapter(FragmentManager fm) { super(fm); }

    @Override public Fragment getItem(int position) {
        if (position == 1) return new FavoritosFragment();
        if (position == 2) return new PerfilFragment();
        return new MascotasFragment();
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        if (position == 1) favoritos = (FavoritosFragment) fragment;
        return fragment;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        if (object == favoritos) favoritos = null;
        super.destroyItem(container, position, object);
    }

    @Override public int getCount() { return 3; }
    @Override public CharSequence getPageTitle(int position) {
        return position == 0 ? "MASCOTAS" : position == 1 ? "FAVORITOS" : "PERFIL";
    }
    public void refrescarFavoritos() { if (favoritos != null) favoritos.refrescar(); }
}
