package com.example.medicinamovil.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medicinamovil.MainActivity;
import com.example.medicinamovil.ObjetosNat.Habitacion;
import com.example.medicinamovil.ObjetosNat.Paciente;
import com.example.medicinamovil.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEnfermeroRecordatorios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEnfermeroRecordatorios extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String[]> info=new ArrayList<>();

    ListView listaRecordatorios;

    public FragmentEnfermeroRecordatorios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEnfermeroCalendario.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEnfermeroRecordatorios newInstance(String param1, String param2) {
        FragmentEnfermeroRecordatorios fragment = new FragmentEnfermeroRecordatorios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_enfermero_calendario, container, false);
        //Actualizacion de fecha
        //fecha=(TextView) view.findViewById(R.id.tvFecha);
        //SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        //String fechaFormato = df.format(Calendar.getInstance().getTime());
        //fecha.setText(fechaFormato);

        //LisView de medicamentos
        //obtenerMedicamentos();

        info=obtenerMapa();
       // AdaptadorRecordatorios adaptadorRecordatorios = new AdaptadorRecordatorios(view.getContext(), info);

        //String idCedulaUsuario = (String)getArguments().getString("idCedulaUsuario");
        //cuadroAlerta.putExtra("idCedulaUsuario", idCedulaUsuario);
        listaRecordatorios=(ListView) view.findViewById(R.id.lvRecordatorios);
        listaRecordatorios.setAdapter(new AdaptadorRecordatorios(view.getContext(), info));

        return view;
    }

    private ArrayList<String[]> obtenerMapa(){
        //info.clear();
        ArrayList<String[]> info=new ArrayList<>();
        ArrayList<Paciente> pacientes= MainActivity.getPacientes();
        for(Paciente paciente:pacientes){
            HashMap<Integer, String[]> recetaIndividual=paciente.getReceta();
            for(Integer idMedicina:recetaIndividual.keySet()){
                info.add(new String[]{String.valueOf(idMedicina),recetaIndividual.get(idMedicina)[0],String.valueOf(paciente.getNumeroHabitacion())});
                //info.add(new String[]{String.valueOf(idMedicina),recetaIndividual.get(idMedicina)[0],String.valueOf(paciente.getNumeroHabitacion()),paciente.getCedula()});

            }
        }
        System.out.println(info.toString());
        return info;
    }
}