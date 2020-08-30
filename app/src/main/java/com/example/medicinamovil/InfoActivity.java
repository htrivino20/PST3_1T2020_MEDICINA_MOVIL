package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info3);
        TextView nombre = (TextView) findViewById(R.id.tvNombreMedicamento);
        TextView descripcion = (TextView) findViewById(R.id.tvDescripcion);

        ImageView imagenMedicina = (ImageView) findViewById(R.id.ivMedicinaInfo);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        System.out.println(b);

        if(b!=null){
            nombre.setText((String) b.get("medicinaNombre"));
            Picasso.get().load((String) b.get("medicinaImagen")).into(imagenMedicina);
            descripcion.setText((String) b.get("medicinaDescripcion"));

        }
    }
}