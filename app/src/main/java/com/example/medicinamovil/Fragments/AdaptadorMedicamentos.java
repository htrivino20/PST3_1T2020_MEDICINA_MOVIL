package com.example.medicinamovil.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicinamovil.InfoActivity;
import com.example.medicinamovil.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorMedicamentos extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    private String[][] datos=null;

    private ArrayList<Integer> tags=new ArrayList<>();
    private static ArrayList<String> medicamentosSolicitados=new ArrayList<>();

    public AdaptadorMedicamentos(Context contexto, String[][] datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        medicamentosSolicitados.clear();
        final View vista = inflater.inflate(R.layout.elementos_lista_medicamentos, null);

        ImageView imagenMedicina = (ImageView) vista.findViewById(R.id.ivMedicina);
        Picasso.get().load(datos[i][1]).into(imagenMedicina);

        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvNombreMedicina);
        nombreMedicina.setText(datos[i][0]);

        TextView moreInfo=(TextView) vista.findViewById(R.id.tvInfo);
        moreInfo.setTag(i);

        final CheckBox checkBox=(CheckBox) vista.findViewById(R.id.checkBox);
        checkBox.setTag(i);

        if(tags.contains(checkBox.getTag())){
            checkBox.setChecked(true);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    tags.add((Integer)v.getTag());
                    //Se agrega el nombre del medicmento a los medicamentos solicitados
                    medicamentosSolicitados.add(datos[(Integer)v.getTag()][0]);
                }
                else{
                    //El tag es para mantener el check
                    tags.remove((Integer)v.getTag());
                    medicamentosSolicitados.remove(datos[(Integer)v.getTag()][0]);
                }

                //Intent mostrarInfo = new Intent(contexto, InfoActivity.class);
                //mostrarInfo.putExtra("nombreMedicina", datos[(Integer)v.getTag()][0]);
                //mostrarInfo.putExtra("imagenMedicina", datos[(Integer)v.getTag()][1]);
                //contexto.startActivity(mostrarInfo);
            }
        });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarInfo = new Intent(contexto, InfoActivity.class);
                mostrarInfo.putExtra("nombreMedicina", datos[(Integer)v.getTag()][0]);
                mostrarInfo.putExtra("imagenMedicina", datos[(Integer)v.getTag()][1]);
                contexto.startActivity(mostrarInfo);
            }
        });

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

    public static ArrayList<String> getMedicamentosSolicitados() {
        return medicamentosSolicitados;
    }
}
