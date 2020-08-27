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

    public Usuario(String cedula, String contrasena) {
        this.cedula = cedula;
        this.contrasena = contrasena;

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



    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }


    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


}
