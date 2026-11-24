package com.lostomates.mascotas;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

/** Sesión de mascotas y gustos guardados localmente. No utiliza backend. */
public final class MascotaRepository {
    private static MascotaRepository INSTANCE;
    private final List<Mascota> mascotas;
    private final SharedPreferences prefs;

    private MascotaRepository(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("mascotas", Context.MODE_PRIVATE);
        mascotas = MascotaDataSet.crear();
        for (Mascota mascota : mascotas) {
            mascota.gustado = prefs.getBoolean("liked_" + mascota.id, false);
            mascota.rating = mascota.ratingInicial + (mascota.gustado ? 1 : 0);
        }
    }

    public static synchronized MascotaRepository get(Context context) {
        if (INSTANCE == null) INSTANCE = new MascotaRepository(context);
        return INSTANCE;
    }

    public List<Mascota> todas() { return mascotas; }

    public List<Mascota> favoritas() {
        List<Mascota> lista = new ArrayList<Mascota>();
        for (Mascota m : mascotas) if (m.gustado) lista.add(m);
        return lista;
    }

    public void cambiarGusto(Mascota mascota) {
        mascota.gustado = !mascota.gustado;
        mascota.rating = mascota.ratingInicial + (mascota.gustado ? 1 : 0);
        prefs.edit().putBoolean("liked_" + mascota.id, mascota.gustado).apply();
    }
}
