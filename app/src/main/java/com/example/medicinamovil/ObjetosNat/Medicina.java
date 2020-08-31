package com.example.medicinamovil.ObjetosNat;

import java.time.LocalDate;

public class Medicina {
    private String descripcion;
    private String dosis;
    private int id;
    private String imagen;
    private String nombre;

    public Medicina() {
    }


    public Medicina(String descripcion, String dosis, int id, String imagen, String nombre) {
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return nombre;

    }
}
