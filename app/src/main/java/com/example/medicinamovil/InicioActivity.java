package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medicinamovil.ObjetosNat.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class InicioActivity extends AppCompatActivity {
    //private static ArrayList<Paciente> pacientes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        iniciarParametros();

    }


    public void crearCuenta(View view){
        Intent nuevaCuenta = new Intent(this, GenerarContrasena.class);
        startActivity(nuevaCuenta);
        //finish();
    }

    public void iniciarSesion(View view){
        Intent principalA = new Intent(this, IniciarSesionActivity.class);

        startActivity(principalA);
    }

    public void iniciarParametros(){
        ArrayList<Paciente> pacientesObtenidos = MainActivity.getPacientes();
        for (Paciente pacientes: pacientesObtenidos
             ) {
            System.out.println("CONTRASENAS USUARIO");
            System.out.println(pacientes.getContrasena());
        }
    }




}