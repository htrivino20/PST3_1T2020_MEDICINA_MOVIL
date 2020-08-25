package com.example.medicinamovil.ObjetosNat;

import android.widget.Button;
import android.widget.EditText;


//Clase oara regitro de usaurio

public class Usuario {
    //Atributos de la clase usuario
    private String cargo;
    private String cedula;
    private String  contrasena;
    private int numeroHabitacion;

    public Usuario(String cargo, String cedula, String contrasena) {
        this.cargo = cargo;
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public Usuario(String cargo, String cedula, String contrasena, int numeroHabitacion) {
        this.cargo = cargo;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.numeroHabitacion = numeroHabitacion;
    }



    public Usuario() {
    }

    public boolean equals(Object obj){
        if(obj!= null){
            if(obj instanceof Usuario){
                Usuario u = (Usuario)obj;
                if(this.getCedula() == u.getCedula()){
                    return true;
                }
            }
        }
        return false;
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


}
