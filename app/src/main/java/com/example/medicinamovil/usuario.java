package com.example.medicinamovil;

import android.widget.Button;
import android.widget.EditText;


//Clase oara regitro de usaurio

public class usuario {
    //Atributos de la clase usuario

    String cedula;
    String  contrasena;
    String cargo;
    String habitacion;

    public usuario(String cedula, String contrasena, String cargo,String habitacion) {

        this.cedula = cedula;
        this.contrasena = contrasena;
        this.cargo = cargo;
        this.habitacion= habitacion;

    }



    public String getCedula() {
        return cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getCargo() { return cargo; }

    public String getHabitacion() { return habitacion; }


}
