package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearCuentaActivity extends AppCompatActivity {

    //DATOS ENVIADOS A LA BASE DE DATOS EN FIREBASE
//Declaracion de los  objetos
    EditText txtCedula;

    EditText txtContrasena;
    Button btnRegistrar;

    //Declaramos la variable publica de referencia a la base de datos
    private DatabaseReference dataUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        dataUsuario = FirebaseDatabase.getInstance().getReference("usuario");

        txtCedula= (EditText)findViewById(R.id.textCedula);
        txtContrasena=(EditText)findViewById(R.id.etContrasena);

        btnRegistrar=(Button)findViewById(R.id.buttonRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarClase();
            }
        });

    }

    public void registrarClase(){
        String cedula=txtCedula.getText().toString();
        String contrasena=txtContrasena.getText().toString();

        if (!TextUtils.isEmpty(cedula)){
            String id = dataUsuario.push().getKey();
            usuario usuario1=new usuario(id,cedula,contrasena);
            dataUsuario.child("Usuarios").child(id).setValue(usuario1);
            Toast toast = Toast.makeText(getApplicationContext(),"Se ha creado su cuenta!",Toast.LENGTH_LONG);
            toast.show();

        }

    }

    public void regresar(View view) {
        finish();
    }

    public void crearCuenta(View view){




        //EditText nombre = (EditText) findViewById(R.id.etNombre);
        //EditText apellidos = (EditText) findViewById(R.id.etNombre);
        //EditText usuario = (EditText) findViewById(R.id.etUsuario);
        //EditText correo = (EditText) findViewById(R.id.etCorreo);
        //EditText celular = (EditText) findViewById(R.id.etCelular);
        //EditText genero = (EditText) findViewById(R.id.etGenero);

        //String[] info={nombre.getText().toString(),
        //        apellidos.getText().toString(),
        //        correo.getText().toString(),
        //        celular.getText().toString(),
        //        genero.getText().toString()};
        //PrincipalActivity.usuarios.put(usuario.getText().toString(), info);

        Toast toast = Toast.makeText(getApplicationContext(),"Se ha creado su cuenta!",Toast.LENGTH_LONG);
        toast.show();
        finish();


    }
}