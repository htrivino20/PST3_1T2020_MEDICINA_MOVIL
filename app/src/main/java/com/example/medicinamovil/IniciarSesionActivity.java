package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinamovil.ObjetosNat.Enfermero;
import com.example.medicinamovil.ObjetosNat.Paciente;
import com.example.medicinamovil.ObjetosNat.Usuario;

import java.util.ArrayList;

public class IniciarSesionActivity extends AppCompatActivity {
    EditText cedula;
    EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        cedula=(EditText) findViewById(R.id.etCedula);
        contrasena=(EditText) findViewById(R.id.etContrasena);
    }

    public void iniciarSesion(View view){
        tipoUsuario(cedula.getText().toString(), contrasena.getText().toString());
        Usuario tipo=MainActivity.getUser();
        if(tipo==null){
            Toast.makeText(getApplicationContext(),"No se encontro el usuario",Toast.LENGTH_SHORT).show();
        }
        else if(tipo instanceof Enfermero){
            Intent principalA = new Intent(this, PrincipalEnfermeroActivity.class);
            startActivity(principalA);
        }
        else if(tipo instanceof Paciente){
            Intent principalA = new Intent(this, PrincipalPacienteActivity.class);
            startActivity(principalA);
        }
    }

    public void pruebaEnfermero(View view){
        Intent principalA = new Intent(this, PrincipalEnfermeroActivity.class);
        startActivity(principalA);
        //finish();
    }

    public void tipoUsuario(String cedula, String contrasena){
        ArrayList<Enfermero> enfs =MainActivity.getEnfermeros();
        ArrayList<Paciente> pacs =MainActivity.getPacientes();

        for(Enfermero enf: enfs){
            if ((enf.getCedula().equals(cedula))&&(contrasena.equals(enf.getContrasena()))){
                MainActivity.setUser(enf);
            }
        }

        for(Paciente pac: pacs){
            if ((pac.getCedula().equals(cedula))&&(contrasena.equals(pac.getContrasena()))){
                MainActivity.setUser(pac);
            }
        }
        MainActivity.setUser(null);
    }

    public void generarContrasena(View view){
        Intent nuevaCuenta = new Intent(this, GenerarContrasena.class);
        startActivity(nuevaCuenta);
        //finish();
    }
}