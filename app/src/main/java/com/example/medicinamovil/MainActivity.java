package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.medicinamovil.ObjetosNat.Medicina;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private ImageView imagenInicio;
    private String photo;
    private static HashMap<Integer, Medicina> dataMedicina =new HashMap<>();
    private DatabaseReference db_reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db_reference= FirebaseDatabase.getInstance().getReference().child("Medicina");

        dataMedicina=solicitarMedicina();
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

}