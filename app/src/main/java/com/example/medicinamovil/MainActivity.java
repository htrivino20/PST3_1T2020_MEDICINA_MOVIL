package com.example.medicinamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    private ImageView imagenInicio;
    private String photo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}