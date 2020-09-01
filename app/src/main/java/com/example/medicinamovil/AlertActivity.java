package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
    String idmedicamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho= medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int) (ancho*0.7), (int) (alto*0.7));
        TextView medicinas = (TextView) findViewById(R.id.tvMedicinas);

        ImageView imagenMedicina = (ImageView) findViewById(R.id.ivAlerta);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        db_reference = FirebaseDatabase.getInstance().getReference().child("Solicitudes");
         idmedicamentos=(String) b.get("idmedicinas");
        System.out.println("Cuantos ids de medicamentos: " + idmedicamentos);
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
       // for (Integer i: idMedicamentosSolicitados) {

            if (items.size() > 0) {
                String clave = items.get(items.size() - 1).getKey().toString();
                Integer cambio = Integer.parseInt(clave);
                String claveAgg = String.valueOf(cambio + 1);
                String [] array = idmedicamentos.split(" ");
                System.out.println(array.toString());
                for (Integer i: idMedicamentosSolicitados){
                db_reference.child(claveAgg).child("cedula").setValue(idCedula);
                db_reference.child(claveAgg).child("idMedicina").setValue(idmedicamentos);

                }


            } else {
                //for (Integer i : idMedicamentosSolicitados) {
                db_reference.child("1").child("cedula").setValue(idCedula);
                db_reference.child("1").child("idMedicina").setValue(idMedicamentosSolicitados);
                //}
         }


            Toast.makeText(getApplicationContext(), "Su solicitud ha sido enviada", Toast.LENGTH_SHORT).show();
        //}
        finish();
    }

    public void salir(View view){
        finish();
    }

}