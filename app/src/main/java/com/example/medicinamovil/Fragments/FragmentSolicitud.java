package com.example.medicinamovil.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.medicinamovil.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSolicitud#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSolicitud extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String[]> rows= new ArrayList<>();
    private String[] header={"Medicamentos", "Hora","Estado"};
    private TableLayout tableLayout;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSolicitud() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSolicitud.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSolicitud newInstance(String param1, String param2) {
        FragmentSolicitud fragment = new FragmentSolicitud();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solicitud, container, false);

        //Mostrar medicamentos diarios
        agregarMedicamentos();
        //tableLayout=(TableLayout) view.findViewById(R.id.tablaMedicamentos);
        //TablaDinamica tableDynamic=new TablaDinamica(tableLayout, container.getContext());
        //tableDynamic.addHeader(header);

        //tableDynamic.addData(rows);




        return view;
    }

    public void agregarMedicamentos(){
        String[] receta1={"Paracetamol","8 am","Pendiente"};
        String[] receta2={"Ibuprofeno","10 am","Entregado"};
        String[] receta3={"Trileptal","10 am","Entregado"};
        String[] receta4={"Acnotin","12 am","Pendiente"};
        String[] receta5={"Pastilla5","1 pm","Pendiente"};

        rows.add(receta1);
        rows.add(receta2);
        rows.add(receta3);
        rows.add(receta4);
        rows.add(receta5);


    }
}