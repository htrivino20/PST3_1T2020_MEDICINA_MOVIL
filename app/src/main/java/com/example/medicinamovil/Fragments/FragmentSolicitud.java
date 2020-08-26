package com.example.medicinamovil.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

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
    //private String[] header={"Medicamentos", "Hora","Estado"};
    private TableLayout tableLayout;

    private String[][] datos=null;
    ListView listaMedicamentos;
    Button botonEnviarSolicitud;



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
        listaMedicamentos=(ListView) view.findViewById(R.id.lvMedicamentos);
        listaMedicamentos.setAdapter(new AdaptadorMedicamentos(view.getContext(), datos));
        botonEnviarSolicitud=(Button)view.findViewById(R.id.botonEnviarSolicitud);

        //Accion del boton
        botonEnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKKKKK: Tamaño de array");
                ArrayList<String> medicamentosSolicitados=AdaptadorMedicamentos.getMedicamentosSolicitados();
                System.out.println(medicamentosSolicitados.size());
                if(!(medicamentosSolicitados.isEmpty())){
                    Toast.makeText(getActivity().getBaseContext(),"Peticion con "+medicamentosSolicitados.size()+" medicamentos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity().getBaseContext(),"No ha seleccionado algun medicamento", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void agregarMedicamentos(){

            //Aqui se debe hacer la consulta de los datos de acuerdo con el id del paciente
            datos=new String[][]{ new String[] {"Paracetamol","https://www.pngkit.com/png/full/694-6945463_pastillas-tempra-caja-de-500mg-paracetamol.png"},
                    new String[] {"Ibuprofeno","https://www.moncloa.com/wp-content/uploads/2020/07/ibuprofeno.jpg"},
                    new String[] {"Trileptal","https://colsubsidio.vteximg.com.br/arquivos/ids/157682-1200-1200/7702635718338.jpg?v=637108450484170000"},
                    new String[]  {"Acnotin","https://www.bago.com.ec/wp-content/uploads/2020/03/ACNOTIN-adicional.png"},
                    new String[] {"Norlevo","https://www.farma-vazquez.com/13227-large_default/norlevo-15-mg-1-comprimido.jpg"}
            };

    }

    public void enviarSolicitud(View view){
        ArrayList<String> medicamentosSolicitados=AdaptadorMedicamentos.getMedicamentosSolicitados();
        if(!(medicamentosSolicitados.isEmpty())){
            Toast toast=Toast.makeText(getActivity().getBaseContext(),"Se realiza la peticion", Toast.LENGTH_SHORT);
        }
        else{
            Toast toast=Toast.makeText(getActivity().getBaseContext(),"No ha seleccionado algun medicamento", Toast.LENGTH_SHORT);
        }

    }
}