package com.example.medicinamovil.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.medicinamovil.MainActivity;
import com.example.medicinamovil.R;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEnfermeroSolicitud#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEnfermeroSolicitud extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listaRecordatorios;

    public FragmentEnfermeroSolicitud() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEnfermeroSolicitud.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEnfermeroSolicitud newInstance(String param1, String param2) {
        FragmentEnfermeroSolicitud fragment = new FragmentEnfermeroSolicitud();
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

        View view = inflater.inflate(R.layout.fragment_enfermero_solicitud, container, false);
        listaRecordatorios=(ListView) view.findViewById(R.id.lvSolicitudes);
        ArrayList<String[]> info=obtenerMapa();
        System.out.println("LUEGO DE OBTENER EL MAPA DE INFO");

        for(String[] inf:info){
            System.out.println(inf[0]+"   "+inf[1]);
        }


//AQUI ES DONDE SE CAE, ESPEREMOS APROBAR
        AdaptadorSolicitudes adaptadorSolicitudes = new AdaptadorSolicitudes(view.getContext(),info);
        listaRecordatorios.setAdapter(adaptadorSolicitudes);
        return view;
    }

    private ArrayList<String[]> obtenerMapa(){
        ArrayList<String[]> info=MainActivity.getSolicitudes();
        System.out.println("OBTENIENDO EL VALOR DE INFO");
        for(String[] inf:info){
            System.out.println(inf[0]+"   "+inf[1]);
        }
        return info;
    }
}