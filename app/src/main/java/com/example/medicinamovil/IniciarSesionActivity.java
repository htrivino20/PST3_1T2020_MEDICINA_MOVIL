package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IniciarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
    }

    public void iniciarSesion(View view){
        Intent principalA = new Intent(this, PrincipalPacienteActivity.class);
        //PrincipalActivity.setUser(usuario.getText().toString());
        //System.out.println("usuario en login: "+usuario.getText());
        startActivity(principalA);
        //finish();
    }

    public void pruebaEnfermero(View view){
        Intent principalA = new Intent(this, PrincipalEnfermeroActivity.class);
        //PrincipalActivity.setUser(usuario.getText().toString());
        //System.out.println("usuario en login: "+usuario.getText());
        startActivity(principalA);
        //finish();
    }




}