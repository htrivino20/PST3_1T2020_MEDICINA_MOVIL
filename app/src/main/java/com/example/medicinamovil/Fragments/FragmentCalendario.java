package com.example.medicinamovil.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinamovil.R;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCalendario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCalendario extends Fragment {
    TextView fecha;
    ListView listaMedicamentosDiarios;
    private String[] header={"Medicamentos", "Hora","Estado"};
    private ArrayList<String[]> rows= new ArrayList<>();

    String[][] datos={
            {"Paracetamol","8 am","Pendiente"},
            {"Ibuprofeno","10 am","Entregado"},
            {"Trileptal","10 am","Entregado"},
            {"Acnotin","12 am","Pendiente"},
            {"Norlevo","1 pm","Pendiente"}
    };


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCalendario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCalendario newInstance(String param1, String param2) {
        FragmentCalendario fragment = new FragmentCalendario();
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
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        //Actualizacion de fecha
        fecha=(TextView) view.findViewById(R.id.tvFecha);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String fechaFormato = df.format(Calendar.getInstance().getTime());
        fecha.setText(fechaFormato);

        //LisView de medicamentos
        listaMedicamentosDiarios=(ListView) view.findViewById(R.id.lvMedicamentosDiarios);
        listaMedicamentosDiarios.setAdapter(new Adaptador(view.getContext(), datos));
        return view;
    }


}