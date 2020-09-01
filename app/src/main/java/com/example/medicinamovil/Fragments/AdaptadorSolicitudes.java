package com.example.medicinamovil.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.medicinamovil.MainActivity;
import com.example.medicinamovil.ObjetosNat.Medicina;
import com.example.medicinamovil.ObjetosNat.Paciente;
import com.example.medicinamovil.ObjetosNat.Usuario;
import com.example.medicinamovil.ObjetosNat.Variables;
import com.example.medicinamovil.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AdaptadorSolicitudes extends BaseAdapter {
    private Context contexto;
    private static ArrayList <String[]> info;
    private static LayoutInflater inflater = null;
    private static ArrayList<String> idUsuarios=new ArrayList<>();
    private Integer[] idMedicina;
    private DatabaseReference databaseReference;

    String cedula;
    String nombre;
    Integer idMedicinaE;
    public AdaptadorSolicitudes(Context contexto, ArrayList <String[]> info) {
        // La clave es la cedula del usuario y el valor es el id de la medicina
        this.contexto = contexto;
        this.info = info;
        inflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //0 es id usuario y 1 es id medicina
        idUsuarios.clear();
        final View vista = inflater.inflate(R.layout.elementos_lista_solicitudes, null);

        databaseReference = FirebaseDatabase.getInstance().getReference().child(Variables.SOLICITUDES_FI);
        for (String[] a : info) {
            idUsuarios.add(a[0]);
        }
        System.out.println("MATRIZ ENTRADA: "+info.size());

        TextView nombreMedicina = (TextView) vista.findViewById(R.id.tvMedicina);
        TextView nombrePaciente = (TextView) vista.findViewById(R.id.tvNombrePaciente);
        TextView idPaciente = (TextView) vista.findViewById(R.id.tvId);
        cedula=MainActivity.getSolicitudes().get(0)[0];
        System.out.println("ESTAS SON SOLICITUDES=======>"+cedula);
        System.out.println("ESTAS SON SOLICITUDES id "+MainActivity.getSolicitudes().get(0)[1]);
        idPaciente.setText(cedula);
        nombreMedicina.setText(obtenerNombreMedicina(Integer.parseInt(info.get(i)[1])));
        Paciente p=obtenerPaciente(cedula);
        nombrePaciente.setText(p.getNombre());

        ImageView aceptar= (ImageView) vista.findViewById(R.id.ivCheck);
        ImageView rechazar= (ImageView) vista.findViewById(R.id.ivCross);



        aceptar.setTag(i);
        rechazar.setTag(i);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idTag=(Integer) v.getTag();
                System.out.println("Numero: "+ idTag);
                Paciente p=obtenerPaciente(idUsuarios.get((Integer) v.getTag()));
                final String idMedicina= info.get((Integer) v.getTag())[1];
                final Integer idRemover = Integer.parseInt(idMedicina);
                System.out.println("idMedicina" + idMedicina);
                info.remove(idTag);

                final String idPaciente = p.getCedula();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data: dataSnapshot.getChildren()){
                            String cedula = String.valueOf(data.child("cedula").getValue());
                            //DataSnapshot medicinasData = data.child("")
                            //Integer inte = (Integer)data.child(idMedicina).getValue();
                            DataSnapshot idMedicinas = data.child("idMedicina");

                            /*Integer integer = (Integer) idMedicinas.getValue();
                            if(integer == idRemover){

                            }*/

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //
                //AQUI TAMBIEN SE DEBE ENVIAR A LA BASE DE DATOS LA SEÑAL DE ENVIO
                setDato();

                Toast.makeText(v.getContext(),"Se han enviado los medicamentos a la habitacion "+p.getNumeroHabitacion(),Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        rechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idTag=(Integer) v.getTag();
                Paciente p=obtenerPaciente(idUsuarios.get((Integer) v.getTag()));
                info.remove(idTag);

                Toast.makeText(v.getContext(),"Se han rechazado la solicitud",Toast.LENGTH_SHORT).show();

                notifyDataSetChanged();
            }
        });
        return vista;
    }

    @Override
    public int getCount() {
        System.out.println("VALOR DE INFO"+info.size());
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


    //METODO QUE OBTIENE EL NOMBRE DE LA MEDICINA
    //A traves de su Id

    public String obtenerNombreMedicina(int idMedicina){
        HashMap<Integer, Medicina> dataMedicina = MainActivity.getDataMedicina();
        if(dataMedicina.containsKey(idMedicina)){
            return dataMedicina.get(idMedicina).getNombre();
        }
        return "";

    }

    public Paciente obtenerPaciente(String idUsuario){
        ArrayList<Paciente> pacientes= MainActivity.getPacientes();
        for(Paciente p:pacientes){
            if(idUsuario.equals(p.getCedula())){
                System.out.println("Cedulas pacientes================> "+p.getCedula());
                return p;
            }
        }
        return null;

    }

    public void setDato(){
        //Verificar el cambio de estado de pastilla dentro de la receta y verificar que se actualicen los datos

    };

}
