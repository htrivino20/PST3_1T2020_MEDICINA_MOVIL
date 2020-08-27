package com.example.medicinamovil.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicinamovil.InfoActivity;
import com.example.medicinamovil.PrincipalPacienteActivity;
import com.example.medicinamovil.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AdaptadorMedicamentosPaciente extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private Context contexto;
    private HashMap<Integer, String[]> receta;
    private static ArrayList<Integer> clavesMedicina =new ArrayList<>();


    private String photoEntregado="https://upload-icon.s3.us-east-2.amazonaws.com/uploads/icons/png/6549974331557740369-512.png";
    private String photoPendiente="https://cdn3.iconfinder.com/data/icons/flat-office-icons-1/140/Artboard_1-11-512.png";

    public AdaptadorMedicamentosPaciente(Context contexto, HashMap<Integer, String[]> receta) {
        this.contexto = contexto;
        this.receta = receta;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.elementos_lista_medicamentos_paciente, null);
        //Agregar los keys en un ArrayList
        Set<Integer> idMedicina=receta.keySet();
        Iterator<Integer> itr = idMedicina.iterator();
        while(itr.hasNext()){
            clavesMedicina.add(itr.next());
        }

        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvNombreMedicina);


        TextView hora = (TextView) vista.findViewById(R.id.tvHora);
        ImageView imagen = (ImageView) vista.findViewById(R.id.ivEstado);
        nombreMedicina.setText(PrincipalPacienteActivity.getDataMedicina().get(clavesMedicina.get(i)).getNombre());

        hora.setText((receta.get(clavesMedicina.get(i)))[0]);
        TextView moreInfo=(TextView) vista.findViewById(R.id.tvmoreInfo);

        moreInfo.setTag(i);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarInfo = new Intent(contexto, InfoActivity.class);
                mostrarInfo.putExtra("medicinaNombre", PrincipalPacienteActivity.getDataMedicina().get(clavesMedicina.get((Integer)v.getTag())).getNombre());
                mostrarInfo.putExtra("medicinaImagen", PrincipalPacienteActivity.getDataMedicina().get(clavesMedicina.get((Integer)v.getTag())).getImagen());
                mostrarInfo.putExtra("medicinaDescripcion", PrincipalPacienteActivity.getDataMedicina().get(clavesMedicina.get((Integer)v.getTag())).getDescripcion());
                contexto.startActivity(mostrarInfo);
            }
        });

        if((receta.get(clavesMedicina.get(i)))[1].equals("Entregado")){
            Picasso.get().load(photoEntregado).into(imagen);
        }
        else{
            Picasso.get().load(photoPendiente).into(imagen);
        }

        return vista;
    }


    @Override
    public int getCount() {
        return receta.size();
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
