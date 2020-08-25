package com.example.medicinamovil.ObjetosNat;

public class Habitacion {
    private int numeroHabitacion;
    private int ocupada;
    private int ruta;


    public Habitacion(int numeroHabitacion, int ocupada, int ruta) {
        this.numeroHabitacion = numeroHabitacion;
        this.ocupada = ocupada;
        this.ruta = ruta;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Habitacion(int ocupada, int ruta) {
        this.ocupada = ocupada;
        this.ruta = ruta;
    }

    public int getOcupada() {
        return ocupada;
    }

    public void setOcupada(int ocupada) {
        this.ocupada = ocupada;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }
}
