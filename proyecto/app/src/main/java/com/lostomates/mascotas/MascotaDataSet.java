package com.lostomates.mascotas;

import java.util.ArrayList;
import java.util.List;

/** Datos dummy: la fuente de datos que consume el Adapter. */
public final class MascotaDataSet {
    private MascotaDataSet() { }

    public static List<Mascota> crear() {
        List<Mascota> items = new ArrayList<Mascota>();
        items.add(new Mascota(1, "Max", R.drawable.pet_max, 12));
        items.add(new Mascota(2, "Luna", R.drawable.pet_luna, 8));
        items.add(new Mascota(3, "Rocky", R.drawable.pet_rocky, 15));
        items.add(new Mascota(4, "Nala", R.drawable.pet_nala, 6));
        items.add(new Mascota(5, "Toby", R.drawable.pet_toby, 9));
        items.add(new Mascota(6, "Misha", R.drawable.pet_misha, 4));
        items.add(new Mascota(7, "Bella", R.drawable.pet_bella, 7));
        items.add(new Mascota(8, "Coco", R.drawable.pet_coco, 11));
        items.add(new Mascota(9, "Zeus", R.drawable.pet_zeus, 5));
        return items;
    }

    /** Exactamente cinco items dummy para la actividad solicitada. */
    public static List<Mascota> ultimasCincoDummy() {
        List<Mascota> all = crear();
        return new ArrayList<Mascota>(all.subList(0, 5));
    }
}
