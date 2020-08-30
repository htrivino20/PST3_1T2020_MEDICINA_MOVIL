package com.example.medicinamovil.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinamovil.InfoActivity;
import com.example.medicinamovil.MainActivity;
import com.example.medicinamovil.ObjetosNat.Habitacion;
import com.example.medicinamovil.ObjetosNat.Medicina;
import com.example.medicinamovil.PrincipalPacienteActivity;
import com.example.medicinamovil.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AdaptadorRecordatorios extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context contexto;
    private static HashMap<Integer, String[]>  info;
    private static ArrayList<Integer> habitaciones=new ArrayList<>();
    //private static ArrayList<Integer> clavesMedicina =new ArrayList<>();


    public AdaptadorRecordatorios(Context contexto, HashMap<Integer, String[]> info) {
        this.contexto = contexto;
        this.info = info;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        habitaciones.clear();
        final View vista = inflater.inflate(R.layout.elementos_lista_recordatorios, null);
        for (Integer habitacion : info.keySet()) {
            habitaciones.add(habitacion);
        }
        //String 0 debe ser el id de la pastilla
        //String 1 debe ser la hora
        for(Integer habitacion:habitaciones){
            System.out.println("HABITACION DENTRO DE HABITACIONES"+habitacion);
        }


        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvNombreMedicina);
        TextView hora = (TextView) vista.findViewById(R.id.tvHora);
        TextView habitacion = (TextView) vista.findViewById(R.id.tvHabitacion);


        nombreMedicina.setText(obtenerNombreMedicina(Integer.parseInt(info.get(habitaciones.get(i))[0])));
        hora.setText(info.get(habitaciones.get(i))[1]);
        habitacion.setText("Habitacion: "+Integer.toString(habitaciones.get(i)));
        ImageView enviar= (ImageView) vista.findViewById(R.id.ivSend);


        enviar.setTag(i);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //habitaciones.remove(habitaciones.get((Integer) v.getTag()));
                Integer habitacionEnvio=habitaciones.get((Integer) v.getTag());
                info.remove(habitacionEnvio);
                Toast.makeText(v.getContext(),"Se han enviado los medicamentos a la habitacion "+habitacionEnvio,Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });

        return vista;
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public String obtenerNombreMedicina(int idMedicina){
        HashMap<Integer, Medicina> dataMedicina =MainActivity.getDataMedicina();
        if(dataMedicina.containsKey(idMedicina)){
            return dataMedicina.get(idMedicina).getNombre();
        }
        return "";

    }

}
