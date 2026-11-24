package com.lostomates.mascotas;

public class Mascota {
    public final int id;
    public final String nombre;
    public final int imagen;
    public final int ratingInicial;
    public int rating;
    public boolean gustado;

    public Mascota(int id, String nombre, int imagen, int rating) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.ratingInicial = rating;
        this.rating = rating;
    }
}
