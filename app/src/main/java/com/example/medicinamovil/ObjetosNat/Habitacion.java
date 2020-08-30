package com.example.medicinamovil.ObjetosNat;

public class Habitacion {
    private int numeroHabitacion;
    private int ruta;


    public Habitacion(int numeroHabitacion, int ruta) {
        this.numeroHabitacion = numeroHabitacion;
        this.ruta = ruta;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }

    public boolean equals(Object obj){
        if (obj instanceof Habitacion){
            if((((Habitacion) obj).getNumeroHabitacion()==this.numeroHabitacion)){
                return true;
            }

        }
        return false;

    }
}
