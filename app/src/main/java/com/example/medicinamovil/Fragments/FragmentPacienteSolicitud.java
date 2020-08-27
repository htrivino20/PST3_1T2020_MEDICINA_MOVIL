package com.example.medicinamovil.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.medicinamovil.AlertActivity;
import com.example.medicinamovil.ObjetosNat.Medicina;
import com.example.medicinamovil.PrincipalPacienteActivity;
import com.example.medicinamovil.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPacienteSolicitud#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPacienteSolicitud extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private String[] header={"Medicamentos", "Hora","Estado"};
    AdaptadorMedicamentos adaptadorMedicamentos;
    HashMap<Integer, Medicina> dataMedicina =new HashMap<>();
    final HashMap<String,Integer> nombresMedicamentos=new HashMap<>();
    HashMap<Integer, Medicina> medicamentosBusqueda= new HashMap<>();



    //private String[][] datos=null;

    ListView listaMedicamentos;
    Button botonEnviarSolicitud;
    SearchView searchView;
   // HashMap<Integer, Medicina> dataMedicina;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPacienteSolicitud() {
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
    public static FragmentPacienteSolicitud newInstance(String param1, String param2) {
        FragmentPacienteSolicitud fragment = new FragmentPacienteSolicitud();
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
        final View view = inflater.inflate(R.layout.fragment_solicitud, container, false);
        dataMedicina= PrincipalPacienteActivity.getDataMedicina();

        //Mostrar medicamentos diarios
        listaMedicamentos=(ListView) view.findViewById(R.id.lvMedicamentos);
        adaptadorMedicamentos=new AdaptadorMedicamentos(view.getContext(), dataMedicina);
        listaMedicamentos.setAdapter(adaptadorMedicamentos);
        botonEnviarSolicitud=(Button)view.findViewById(R.id.botonEnviarSolicitud);

        //SearchView
        searchView = (SearchView) view.findViewById(R.id.searchView);

        dataMedicina= PrincipalPacienteActivity.getDataMedicina();

        for(Integer i:dataMedicina.keySet()){
            nombresMedicamentos.put(dataMedicina.get(i).getNombre().toLowerCase(),i);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("ON QUERY TEXT SUBMIT:"+query);
                realizarBusqueda(view,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("ON QUERY TEXT CHANGE:" +newText);
                realizarBusqueda(view,newText);
                return false;
            }
        });

        //Accion del boton Enviar solicitud
        botonEnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> idmedicamentosSolicitados=AdaptadorMedicamentos.getTags();
                String medicamentos="";
                String idmedicamentos="";
                if(!(idmedicamentosSolicitados.isEmpty())){
                    for (Integer i:idmedicamentosSolicitados){
                        medicamentos=medicamentos+dataMedicina.get(i)+" ";
                        idmedicamentos=idmedicamentos+i.toString()+" ";

                    }
                    AdaptadorMedicamentos.clearTags();

                    Intent cuadroAlerta = new Intent(view.getContext(), AlertActivity.class);

                    cuadroAlerta.putExtra("medicinas", medicamentos);
                    cuadroAlerta.putExtra("idmedicinas", idmedicamentos);

                    view.getContext().startActivity(cuadroAlerta);
                    realizarBusqueda(view,"");
                }
                else{
                    Toast.makeText(getActivity().getBaseContext(),"No ha seleccionado algun medicamento", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void realizarBusqueda(View view, String query) {
        medicamentosBusqueda.clear();

        for(String nombre: nombresMedicamentos.keySet()){
            if(nombre.contains((query.toLowerCase()))){
                medicamentosBusqueda.put(nombresMedicamentos.get(nombre),dataMedicina.get(nombresMedicamentos.get(nombre)));
            }
        }

        if (!(medicamentosBusqueda.isEmpty())) {
            listaMedicamentos.setAdapter(null);
            listaMedicamentos.setAdapter(new AdaptadorMedicamentos(view.getContext(), medicamentosBusqueda));

        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_LONG).show();
        }
    }


}