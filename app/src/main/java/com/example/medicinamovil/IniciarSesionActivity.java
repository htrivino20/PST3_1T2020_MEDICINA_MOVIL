package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medicinamovil.ObjetosNat.Usuario;

public class IniciarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
    }

    public void iniciarSesion(View view){

        String tipo=tipoUsuario();
        if(tipo==null){
            Toast.makeText(getApplicationContext(),"No se encontro el usuario",Toast.LENGTH_SHORT).show();
        }
        else if(tipo.toLowerCase().equals("enfermero")){
            Intent principalA = new Intent(this, PrincipalEnfermeroActivity.class);
            startActivity(principalA);
        }
        else{
            Intent principalA = new Intent(this, PrincipalPacienteActivity.class);
            startActivity(principalA);
        }
    }

    public void pruebaEnfermero(View view){
        Intent principalA = new Intent(this, PrincipalEnfermeroActivity.class);
        //PrincipalActivity.setUser(usuario.getText().toString());
        //System.out.println("usuario en login: "+usuario.getText());
        startActivity(principalA);
        //finish();
    }

    public String tipoUsuario(){
        boolean existe=true;
        String tipo="enfermero";
        //Verificación de clave y contraseña, se retorna el tipo
        if(existe){
        return tipo;}
        else{
            return null;
        }

    }




}