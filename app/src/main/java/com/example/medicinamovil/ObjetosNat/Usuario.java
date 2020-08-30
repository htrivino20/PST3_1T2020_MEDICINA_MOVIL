package com.example.medicinamovil.ObjetosNat;

import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;


//Clase oara regitro de usaurio

public class Usuario {
    //Atributos de la clase usuario
    private String cedula;
    private String  contrasena;
    private String nombre;

    public Usuario(String cedula, String contrasena,String nombre) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.nombre=nombre;

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



    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
}
