package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinamovil.Fragments.FragmentPerfil;
import com.example.medicinamovil.ObjetosNat.Paciente;
import com.example.medicinamovil.ObjetosNat.Usuario;
import com.example.medicinamovil.ObjetosNat.Variables;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GenerarContrasena extends AppCompatActivity {

    //DATOS ENVIADOS A LA BASE DE DATOS EN FIREBASE
//Declaracion de los  objetos
    EditText txtCedula;
    //EditText txtHabitacion;
    EditText txtContrasena;
    Button btnRegistrar;
    Usuario user;
    String cargo = "Encargado";

    int solicitudEstado = 0;
    //Declaramos la variable publica de referencia a la base de datos
    private DatabaseReference databaseReference;
    private ArrayList<Paciente> pacientes;
    private DatabaseReference reference;
    private EditText txtRepitaContrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_contrasena);
        iniciarVariables();
       // solicitarDatosFirebase();


    }

    public void iniciarVariables(){
        //databaseReference = FirebaseDatabase.getInstance().getReference();

        //Relacionaod la parte grafica  y logica

        txtCedula = (EditText) findViewById(R.id.textCedula);
        txtContrasena = (EditText) findViewById(R.id.etContrasena);
        txtRepitaContrasena = findViewById(R.id.etContrasena2);
        pacientes = MainActivity.getPacientes();
      //  txtHabitacion = (EditText) findViewById(R.id.textCuarto);
        reference = FirebaseDatabase.getInstance().getReference().child(Variables.USUARIO_FI);
    }

    public void regresar(View view) {
        Intent navegador = new Intent(this, InicioActivity.class);
        startActivity(navegador);
    }


    public void cambiarContrasena(View view) {
        //Intent navegador = new Intent(this, InicioActivity.class);
        String cedulaPaciente = txtCedula.getText().toString();
        for (Paciente p: pacientes){
            String cedula = p.getCedula();
            if(cedulaPaciente.equals(cedula)){
                if(txtContrasena.getText().toString().equals(txtRepitaContrasena.getText().toString())){
                    reference.child(cedulaPaciente).child("contrasena").setValue(txtContrasena.getText().toString());
                   limpiar();
                    Toast.makeText(getApplicationContext(),"Cambio realizado con exito",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Verificar que las contrasenas coincidan",Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Usted no se encuentra registrado en el hospital",Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
       // startActivity(navegador);
    }

    public void limpiar(){
        txtCedula.setText("");
        txtContrasena.setText("");
        txtRepitaContrasena.setText("");
    }





}