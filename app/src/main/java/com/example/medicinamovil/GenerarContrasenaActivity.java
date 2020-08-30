package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinamovil.Fragments.FragmentPerfil;
import com.example.medicinamovil.ObjetosNat.Usuario;
import com.example.medicinamovil.ObjetosNat.Variables;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GenerarContrasenaActivity extends AppCompatActivity {

    //DATOS ENVIADOS A LA BASE DE DATOS EN FIREBASE
//Declaracion de los  objetos
    EditText txtCedula;
    EditText txtHabitacion;
    EditText txtContrasena;
    Button btnRegistrar;
    Usuario user;
    String cargo = "Encargado";
    int solicitudEstado = 0;
    //Declaramos la variable publica de referencia a la base de datos
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        iniciarVariables();
       // solicitarDatosFirebase();


    }

    public void iniciarVariables(){
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Relacionaod la parte grafica  y logica

        txtCedula = (EditText) findViewById(R.id.textCedula);
        txtContrasena = (EditText) findViewById(R.id.etContrasena);
      //  txtHabitacion = (EditText) findViewById(R.id.textCuarto);

    }

    //Metodo que registra el usuario
    public void registrarUsuario(View view) {
        DatabaseReference usuarios = databaseReference.child(Variables.USUARIO_FI);
        usuarios.addValueEventListener(new ValueEventListener() {
            String cedula = txtCedula.getText().toString();
            String contrasena = txtContrasena.getText().toString();
            Usuario usuario = new Usuario(cargo,cedula,contrasena);

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                     ) {
                    Usuario userD = snapshot.getValue(Usuario.class);
                    if(userD.equals(usuario)){
                        System.out.println(userD.equals(usuario));
                        Context context = getApplicationContext();
                        CharSequence text = "Ya esta registrado";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }else {
                        cargo = "Paciente";
                        //Habitacion hAsignar = asignarHabitacion();
                        //usuario.setCargo(cargo);
                        //Usuario usuarioPaciente = new Usuario(cargo,cedula,contrasena,hAsignar.getNumeroHabitacion());
                        databaseReference.child(Variables.USUARIO_FI).child(usuario.getCedula()).setValue(usuario);
                        Context context = getApplicationContext();
                        CharSequence text = "Registrado";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*public Habitacion asignarHabitacion(){
        final ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();

        DatabaseReference habitaciones = databaseReference.child(Variables.HABITACIONES_FI);
        habitaciones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot snap: dataSnapshot.getChildren()){
                   Habitacion h = snap.getValue(Habitacion.class);
                   if(h.getOcupada() == 0){
                       habitacionesDisponibles.add(h);
                   }
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Habitacion habitacionDisponible = habitacionesDisponibles.get(0);
        return habitacionDisponible;
    }*/

    public void regresar(View view) {
        finish();
    }


    public void Navigation(View view) {
        Intent navegador = new Intent(this, PrincipalPacienteActivity.class);
        startActivity(navegador);
    }

    public void Enviar(View view){
        Intent i= new Intent(this, FragmentPerfil.class);
       // i.putExtra("user",user.cargo);
        startActivity(i);

    }

}