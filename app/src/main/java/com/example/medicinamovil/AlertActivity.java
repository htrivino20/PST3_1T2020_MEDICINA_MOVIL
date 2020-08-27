package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AlertActivity extends AppCompatActivity {

    String imagen = "https://www.pngkey.com/png/full/129-1296729_alert-icon-png.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert);
        TextView medicinas = (TextView) findViewById(R.id.tvMedicinas);

        ImageView imagenMedicina = (ImageView) findViewById(R.id.ivAlerta);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String idmedicamentos=(String) b.get("idmedicinas");

        if(b!=null){
            medicinas.setText((String) b.get("medicinas"));
            Picasso.get().load(imagen).into(imagenMedicina);

        }

    }

    public void enviar(View view){
        Toast.makeText(getApplicationContext(),"Su solicitud ha sido enviada", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void salir(View view){
        finish();
    }


}