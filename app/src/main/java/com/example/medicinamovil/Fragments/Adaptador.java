package com.example.medicinamovil.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicinamovil.R;
import com.squareup.picasso.Picasso;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    String[][] datos=null;

    private String photoEntregado="https://upload-icon.s3.us-east-2.amazonaws.com/uploads/icons/png/6549974331557740369-512.png";
    private String photoPendiente="https://cdn3.iconfinder.com/data/icons/flat-office-icons-1/140/Artboard_1-11-512.png";

    public Adaptador(Context contexto, String[][] datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.elementos_lista, null);
        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvNombreMedicina);
        TextView hora = (TextView) vista.findViewById(R.id.tvHora);
        ImageView imagen = (ImageView) vista.findViewById(R.id.ivEstado);
        nombreMedicina.setText(datos[i][0]);
        hora.setText(datos[i][1]);

        if(datos[i][2].equals("Entregado")){
            Picasso.get().load(photoEntregado).into(imagen);
        }
        else{
            Picasso.get().load(photoPendiente).into(imagen);
        }
        System.out.println("Vista:"+i);
        return vista;
    }


    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
