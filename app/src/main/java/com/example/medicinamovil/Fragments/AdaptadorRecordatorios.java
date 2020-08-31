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
import com.example.medicinamovil.ObjetosNat.Variables;
import com.example.medicinamovil.PrincipalPacienteActivity;
import com.example.medicinamovil.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AdaptadorRecordatorios extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context contexto;
    private static ArrayList<String[]> info;
    private static ArrayList<Integer> habitaciones=new ArrayList<>();
    private DatabaseReference db_reference;
    private String enviando = "enviando...";
    //private static ArrayList<Integer> clavesMedicina =new ArrayList<>();


    public AdaptadorRecordatorios(Context contexto, ArrayList<String[]> info) {
        this.contexto = contexto;
        this.info = info;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Se manda 0 id pastilla, 1 hora, 2 habitacion
        habitaciones.clear();
        final View vista = inflater.inflate(R.layout.elementos_lista_recordatorios, null);
        for (String[] infoIndividual : info) {
            habitaciones.add(Integer.parseInt(infoIndividual[2]));
        }
        db_reference = FirebaseDatabase.getInstance().getReference();

        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvNombreMedicina);
        TextView hora = (TextView) vista.findViewById(R.id.tvHora);
        TextView habitacion = (TextView) vista.findViewById(R.id.tvHabitacion);

        nombreMedicina.setText(obtenerNombreMedicina(Integer.parseInt((info.get(i))[0])));
        hora.setText(info.get(i)[1]);
        habitacion.setText("Habitacion: "+info.get(i)[2]);
        //String idCedulaPaciente = info.get(i)[3];
        ImageView enviar= (ImageView) vista.findViewById(R.id.ivSend);

        enviar.setTag(i);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int habitacion=Integer.parseInt(info.get((Integer) v.getTag())[2]);
                int id=(Integer) v.getTag();
                // EN EL METODO SETDATO SE DEBE ENVIAR A LA BASE DE DATOS LA SEÑAL DE ENVIO DEL MEDICAMENTO
                db_reference.child(Variables.SOLICITUD_FI).child("ruta").setValue(habitacion);
                //db_reference.child(Variables.USUARIO_FI).child(idCedulaPaciente).child("receta");
                setDato();

                info.remove(id);

                Toast.makeText(v.getContext(),"Se han enviado los medicamentos a la habitacion "+habitacion,Toast.LENGTH_SHORT).show();
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
    public void setDato(){
        //Verificar el cambio de estado de pastilla dentro de la receta y verificar que se actualicen los datos

    };

}
