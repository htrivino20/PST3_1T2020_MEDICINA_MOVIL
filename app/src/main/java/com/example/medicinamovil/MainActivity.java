package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.medicinamovil.ObjetosNat.Enfermero;
import com.example.medicinamovil.ObjetosNat.Medicina;
import com.example.medicinamovil.ObjetosNat.Paciente;
import com.example.medicinamovil.ObjetosNat.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private ImageView imagenInicio;
    private String photo;
    private static HashMap<Integer, Medicina> dataMedicina =new HashMap<>();
    private DatabaseReference db_reference;
    private static ArrayList<Paciente> pacientes=new ArrayList<>();
    private static ArrayList<Enfermero> enfermeros=new ArrayList<>();
    private static Usuario usuarioGeneral;
    private static ArrayList<String[]> solicitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db_reference= FirebaseDatabase.getInstance().getReference().child("Medicina");

        dataMedicina=solicitarMedicina();
        pacientes=solicitarPacientes();
        enfermeros=solicitarEnfermeros();
        solicitudes=solicitarSolicitudes();

        iniciar();
    }

    public void iniciar(){
        photo = "https://i.imgur.com/z41JQZq.png";
        imagenInicio = (ImageView)findViewById(R.id.imagenInicio);
        Picasso.get().load(photo).into(imagenInicio);
        Intent i = new Intent(this, InicioActivity.class);
        sleepImage h1 = new sleepImage(i);
        h1.start();
    }

    class sleepImage extends Thread{
        Intent i;
        public sleepImage(Intent i){
            this.i = i;
        }
        public void run() {
            try {
                Thread.sleep(3000);
                startActivity(i);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public HashMap solicitarMedicina(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String nombr = String.valueOf(snapshot.child("nombre").getValue());
                    String descrip = String.valueOf(snapshot.child("descripcion").getValue());
                    String dosi = String.valueOf(snapshot.child("dosis").getValue());
                    String image = String.valueOf(snapshot.child("imagen").getValue());
                    String idMedi = String.valueOf(snapshot.child("id").getValue());
                    int numEntero = Integer.parseInt(idMedi);
                    dataMedicina.put(numEntero,new Medicina(numEntero,nombr,image,descrip,dosi));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("error");
            }
        }); return dataMedicina;
    }

    public static HashMap<Integer, Medicina> getDataMedicina() {
        return dataMedicina;
    }


    public ArrayList<Paciente> solicitarPacientes(){
        //AQUI SE REALIZA LA LECTURA DE TODOS LOS PACIENTES, SE RETORNA EL ARRAYLIST DE TIPO PACIENTE
        ArrayList<Paciente> p = new ArrayList<>();
        HashMap<Integer, String[]> receta1=new HashMap<>();
        receta1.put(1,new String[]{"12 am","Pendiente"});
        receta1.put(2,new String[]{"1 pm","Pendiente"});

        HashMap<Integer, String[]> receta2=new HashMap<>();
        receta2.put(4,new String[]{"12 am","Pendiente"});
        receta2.put(5,new String[]{"1 pm","Entregada"});

        HashMap<Integer, String[]> receta3=new HashMap<>();
        receta3.put(3,new String[]{"8 am","Entregada"});
        receta3.put(5,new String[]{"10 pm","Pendiente"});

        p.add(new Paciente("0123456789","c",1,receta1,"Hector Trivino"));
        p.add(new Paciente("0123456790","c",2,receta2,"Carlos Andres"));
        p.add(new Paciente("0123456791","c",5,receta3,"Juan Pablo"));

        return p;

    }

    public ArrayList<Enfermero> solicitarEnfermeros(){
        //AQUI SE DEBE REALIZAR LA LECTURA DE TODOS LOS ENFERMEROS, SE RETORNA EL ARRAYLIST DE TIPO ENFERMERO
        ArrayList<Enfermero> enf=new ArrayList<>();
        enf.add(new Enfermero("0987654321","c","Lady Pruna"));
        enf.add(new Enfermero("0923589663","c","Juan Carlos"));
        return enf;

    }

    public static ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
    public static ArrayList<Enfermero> getEnfermeros() {
        return enfermeros;
    }

    public static Usuario getUser() {
        return usuarioGeneral;
    }

    public static void setUser(Usuario user) {
        if (user instanceof Enfermero){
            usuarioGeneral=(Enfermero) user;
        }
        else if (user instanceof Paciente){
            usuarioGeneral=(Paciente) user;
        }
    }

    public ArrayList<String[]> solicitarSolicitudes(){
        ArrayList<String[]> info=new ArrayList<>();
        //AQUI SE DEBE REALIZAR LA LECTURA DE TODAS LAS SOLICITUDES
        info.add(new String[]{"0123456789","1"});
        info.add(new String[]{"0123456790","4"});
        info.add( new String[]{"0123456791","3"});
        info.add( new String[]{"0123456791","1"});
        return info;
    }

    public static ArrayList<String[]> getSolicitudes(){
        return solicitudes;

    }
}