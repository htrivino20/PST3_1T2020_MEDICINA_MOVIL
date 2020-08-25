package com.example.medicinamovil.ObjetosNat;

import android.widget.Button;
import android.widget.EditText;


//Clase oara regitro de usaurio

public class Usuario {
    //Atributos de la clase usuario
    private String cargo;
    private String cedula;
    private String  contrasena;
    private int habitacion;
    private int solicitudEstado;

    public Usuario(String cargo, String cedula, String contrasena, int habitacion, int solicitudEstado) {
        this.cargo = cargo;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.habitacion = habitacion;
        this.solicitudEstado = solicitudEstado;
    }

    public Usuario() {
    }


    public String getCargo() {
        return cargo;
    }

    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public int getSolicitudEstado() {
        return solicitudEstado;
    }

    public void setSolicitudEstado(int solicitudEstado) {
        this.solicitudEstado = solicitudEstado;
    }
}
