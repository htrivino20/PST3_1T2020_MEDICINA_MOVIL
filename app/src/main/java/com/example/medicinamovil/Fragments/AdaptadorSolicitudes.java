package com.example.medicinamovil.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medicinamovil.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdaptadorSolicitudes extends BaseAdapter {
    private Context contexto;
    private static HashMap<Integer, Integer>  info;
    private static LayoutInflater inflater = null;
    private static ArrayList<Integer> idUsuarios=new ArrayList<>();


    public AdaptadorSolicitudes(Context contexto, HashMap<Integer, Integer> info) {
        this.contexto = contexto;
        this.info = info;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.elementos_lista_recordatorios, null);
        for (Integer idUsuario : info.keySet()) {
            idUsuarios.add(idUsuario);
        }

        //TextView idUsuario = (TextView) vista.findViewById(R.id.tvNombreUsuario);
        //TextView id = (TextView) vista.findViewById(R.id.tvHora);
        //TextView habitacion = (TextView) vista.findViewById(R.id.tvHabitacion);





        return vista;
    }

    @Override
    public int getCount() {
        return 0;
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
