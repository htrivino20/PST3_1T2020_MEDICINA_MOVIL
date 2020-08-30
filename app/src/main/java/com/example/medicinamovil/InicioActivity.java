package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medicinamovil.ObjetosNat.Medicina;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

    }


    public void crearCuenta(View view){
        Intent nuevaCuenta = new Intent(this, CrearCuentaActivity.class);
        startActivity(nuevaCuenta);
        //finish();
    }

    public void iniciarSesion(View view){
        Intent principalA = new Intent(this, IniciarSesionActivity.class);
        startActivity(principalA);
    }



}