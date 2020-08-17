package com.example.medicinamovil;

import android.widget.Button;
import android.widget.EditText;


//Clase oara regitro de usaurio

public class usuario {
    //Atributos de la clase usuario
    String claseId;
    String cedula;
    String  contrasena;

    public usuario(String claseId, String cedula, String contrasena) {
        this.claseId = claseId;
        this.cedula = cedula;
        this.contrasena = contrasena;

    }

    public String getClaseId() {
        return claseId;
    }

    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

}
