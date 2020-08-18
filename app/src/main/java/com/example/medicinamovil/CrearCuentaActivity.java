package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinamovil.Fragments.FragmentPerfil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CrearCuentaActivity extends AppCompatActivity {

    //DATOS ENVIADOS A LA BASE DE DATOS EN FIREBASE
//Declaracion de los  objetos
    EditText txtCedula;
    EditText txtHabitacion;
    EditText txtContrasena;
    Button btnRegistrar;
    usuario user;
    //Declaramos la variable publica de referencia a la base de datos
    private DatabaseReference dataUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        //instaciando el objeto que se enviara a la base de datos en este caso se enviara un obketo de tipo usaurio

        dataUsuario = FirebaseDatabase.getInstance().getReference("usuario");

        //Relacionaod la parte grafica  y logica

        txtCedula = (EditText) findViewById(R.id.textCedula);
        txtContrasena = (EditText) findViewById(R.id.etContrasena);
        txtHabitacion = (EditText) findViewById(R.id.textCuarto);

        //asigndo de metodo onclibk para subir los datos a la nube
        btnRegistrar = (Button) findViewById(R.id.buttonRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarusuario();
            }
        });
        solicitarDatosFirebase();


    }

    //Metodo para solicitar datos en Firebase del usuario

    private void solicitarDatosFirebase() {
        //consultar los datos a nuestra base de datos
        dataUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    dataUsuario.child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             user = dataSnapshot.getValue(usuario.class);
                            String cedula = user.getCedula();
                            String cargo = user.getCargo();
                            String habitacion = user.getHabitacion();

                            //Revision de recibo de datos

                           // Log.e("NombreUsuario; ", cedula);
                           // Log.e("CARGO; ", cargo);
                           // Log.e("NumeroHabitacion; ", habitacion);


                            //Log.e("NombreUsuario; ",snapshot.getValue());



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //Metodo que registra el usuario
    public void registrarusuario() {
        String cedula = txtCedula.getText().toString();
        String contrasena = txtContrasena.getText().toString();
        String habitacion = txtHabitacion.getText().toString();

        if (!TextUtils.isEmpty(cedula)) {
            //String id = dataUsuario.push().getKey();
            usuario usuario1 = new usuario(cedula, contrasena, "Paciente", habitacion);
            dataUsuario.child(cedula).setValue(usuario1);
            // dataUsuario.child("Usuarios").child(id).setValue(usuario1);

        }

    }


    public void regresar(View view) {
        finish();
    }


    public void Navigation(View view) {
        Intent navegador = new Intent(this, PrincipalActivity.class);
        startActivity(navegador);
    }

    public void Enviar(View view){
        Intent i= new Intent(this, FragmentPerfil.class);
        i.putExtra("user",user.cargo);
        startActivity(i);

    }

}