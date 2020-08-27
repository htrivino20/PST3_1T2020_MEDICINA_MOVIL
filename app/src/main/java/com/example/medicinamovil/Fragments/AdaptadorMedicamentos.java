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
import com.example.medicinamovil.ObjetosNat.Medicina;
import com.example.medicinamovil.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AdaptadorMedicamentos extends BaseAdapter {

    private static LayoutInflater inflater = null;
    //private static ArrayList<Medicina> medicamentosSolicitados=new ArrayList<>();

    private Context contexto;
    private  Map<Integer, Medicina> dataMedicina;
    private  ArrayList<Integer> clavesMedicina =new ArrayList<>();
    private  static ArrayList<Integer> tags=new ArrayList<>();

    public AdaptadorMedicamentos(Context contexto, HashMap<Integer, Medicina> data ) {
        this.contexto = contexto;
        this.dataMedicina = data;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = inflater.inflate(R.layout.elementos_lista_medicamentos, null);
        vista=actualizarVista(i,vista);
        return vista;
    }

    private View actualizarVista(int i, View vista){
        //clavesMedicina.clear();
        for(Integer codigo:dataMedicina.keySet()) {
            clavesMedicina.add(codigo);
        }

        ImageView imagenMedicina = (ImageView) vista.findViewById(R.id.ivMedicina);
        Picasso.get().load(dataMedicina.get(clavesMedicina.get(i)).getImagen()).into(imagenMedicina);

        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvNombreMedicina);
        nombreMedicina.setText(dataMedicina.get(clavesMedicina.get(i)).getNombre());

        final TextView moreInfo=(TextView) vista.findViewById(R.id.tvInfo);
        moreInfo.setTag(i);

        System.out.println("ID MEDICINA"+dataMedicina.get(clavesMedicina.get(i)).getId());
        System.out.println("VALOR DE I:"+i);

        final CheckBox checkBox=(CheckBox) vista.findViewById(R.id.checkBox);
        checkBox.setTag(dataMedicina.get(clavesMedicina.get(i)).getId());

        if(tags.contains(checkBox.getTag())){
            checkBox.setChecked(true);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    tags.add((Integer)checkBox.getTag());
                }
                else{
                    tags.remove((Integer)checkBox.getTag());
                }
            }
        });
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarInfo = new Intent(contexto, InfoActivity.class);
                mostrarInfo.putExtra("medicinaNombre", dataMedicina.get(clavesMedicina.get((Integer) v.getTag())).getNombre());
                mostrarInfo.putExtra("medicinaImagen", dataMedicina.get(clavesMedicina.get((Integer)v.getTag())).getImagen());
                mostrarInfo.putExtra("medicinaDescripcion", dataMedicina.get(clavesMedicina.get((Integer)v.getTag())).getDescripcion());
                contexto.startActivity(mostrarInfo);
            }
        });
       return vista;
    }

    @Override
    public int getCount(){
        return dataMedicina.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    public static ArrayList<Integer> getTags() {
        return tags;
    }
    public static void clearTags(){
        tags.clear();
    }


}
