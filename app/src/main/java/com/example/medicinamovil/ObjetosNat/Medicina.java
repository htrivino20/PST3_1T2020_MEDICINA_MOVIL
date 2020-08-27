package com.example.medicinamovil.ObjetosNat;

import java.time.LocalDate;

public class Medicina {
    private int id;
    private String dosis;
    private String nombre;
    private String imagen;
    private String descripcion;

    public Medicina(int id ,String nombre, String imagen,String descripcion,String dosis) {
        this.id=id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion=descripcion;
        this.dosis=dosis;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDescripcion(){
        return descripcion;

    }

    public String toString(){
        return nombre;

    }
}
