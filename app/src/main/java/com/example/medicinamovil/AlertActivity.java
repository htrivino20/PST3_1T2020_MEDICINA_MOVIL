package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinamovil.ObjetosNat.Medicina;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class AlertActivity extends AppCompatActivity {

    String imagen = "https://www.pngkey.com/png/full/129-1296729_alert-icon-png.png";
    private ArrayList<Integer> idMedicamentosSolicitados;
    ArrayList<DataSnapshot> items = new ArrayList<>();
    private DatabaseReference db_reference;
    private String idCedula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert);
        TextView medicinas = (TextView) findViewById(R.id.tvMedicinas);

        ImageView imagenMedicina = (ImageView) findViewById(R.id.ivAlerta);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        db_reference = FirebaseDatabase.getInstance().getReference().child("Solicitudes");
        String idmedicamentos=(String) b.get("idmedicinas");
        idCedula = (String) b.get("idCedulaUsuario");
        idMedicamentosSolicitados = (ArrayList<Integer>) b.get("idmedicinasSolicitadas");

        db_reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()
                ) {
                    items.add(d);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*for (Integer id:
             idMedicamentosSolicitados) {
            System.out.println(id);
        }
        System.out.println("Cedula en AlertActivity");
        System.out.println((String)b.get("idCedulaUsuario"));
        */

        if(b!=null){
            medicinas.setText((String) b.get("medicinas"));
            Picasso.get().load(imagen).into(imagenMedicina);

        }

    }

    public void enviar(View view){
        System.out.println("LONGITUD: " + items.size());

        if(items.size() > 0){
            String clave = items.get(items.size()-1).getKey().toString();
            Integer cambio = Integer.parseInt(clave);
            String claveAgg = String.valueOf(cambio +1);
            db_reference.child(claveAgg).child("cedula").setValue(idCedula);
            db_reference.child(claveAgg).child("Medicinas").setValue(agregarSolicitudBaseDatos());

        }else {
            db_reference.child("1").child("cedula").setValue(idCedula);
            db_reference.child("1").child("Medicinas").setValue(agregarSolicitudBaseDatos());
        }

        Toast.makeText(getApplicationContext(),"Su solicitud ha sido enviada", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void salir(View view){
        finish();
    }

    public ArrayList<Medicina> agregarSolicitudBaseDatos(){
        //DataSnapshot dataSnapshot = new DataSnapshot();
        //DatabaseReference dbR = new DatabaseReference()
        ArrayList<Medicina> medicinasAgg = new ArrayList<>();
        HashMap<Integer, Medicina> medicamentosMapa = MainActivity.getDataMedicina();
        for (Integer i: idMedicamentosSolicitados
             ) {
            Medicina medicina = medicamentosMapa.get(i);
            medicinasAgg.add(medicina);
        }
        return medicinasAgg;
    }


}