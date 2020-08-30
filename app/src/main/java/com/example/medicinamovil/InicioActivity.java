package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

    }


    public void crearCuenta(View view){
        Intent nuevaCuenta = new Intent(this, GenerarContrasenaActivity.class);
        startActivity(nuevaCuenta);
        //finish();
    }

    public void iniciarSesion(View view){
        Intent principalA = new Intent(this, IniciarSesionActivity.class);
        startActivity(principalA);
    }



}