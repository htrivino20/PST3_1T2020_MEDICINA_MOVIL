package com.example.medicinamovil.ObjetosNat;

import java.util.HashMap;

public class Paciente extends Usuario {
    int numeroHabitacion;
    HashMap<Integer, String[]> receta;

    public Paciente(String cedula, String contrasena, int numeroHabitacion, HashMap<Integer, String[]> receta, String nombre) {
        super(cedula, contrasena, nombre);
        this.numeroHabitacion = numeroHabitacion;
        this.receta = receta;
    }

    public HashMap<Integer, String[]> getReceta() {
        return receta;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }


}
