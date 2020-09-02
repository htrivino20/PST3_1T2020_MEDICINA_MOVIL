package com.example.medicinamovil.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinamovil.AlertActivity;
import com.example.medicinamovil.MainActivity;
import com.example.medicinamovil.ObjetosNat.Enfermero;
import com.example.medicinamovil.PrincipalEnfermeroActivity;
import com.example.medicinamovil.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPerfil extends Fragment {
    String datos;
    TextView nombre;
    TextView cedula;
    Button boton;

    View vista;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String fotoPaciente="https://www.shareicon.net/data/128x128/2017/01/06/868320_people_512x512.png";
    String fotoEnfermero= "https://www.shareicon.net/data/128x128/2016/08/18/813852_people_512x512.png";
    ImageView fotoUsuariop;

    public FragmentPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPerfil newInstance(String param1, String param2) {
        FragmentPerfil fragment = new FragmentPerfil();
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
        vista=inflater.inflate(R.layout.fragment_perfil, container, false);

        //Inicializando las variables del
        nombre=(TextView)vista.findViewById(R.id.txtNombre);
        cedula=(TextView)vista.findViewById(R.id.textCedula);

        //Se carga los datos
        cargarPerfil(vista);
        boton=(Button) vista.findViewById(R.id.botonSalir);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });



        return vista;
    }

    public void cargarPerfil(View vista){

        nombre.setText(MainActivity.getUser().getNombre());
        cedula.setText(MainActivity.getUser().getCedula());
        fotoUsuariop = (ImageView)vista.findViewById(R.id.ivUsuario);

        if(MainActivity.getUser() instanceof Enfermero){
            Picasso.get().load(fotoEnfermero).into(fotoUsuariop);
        }
        else{
            Picasso.get().load(fotoPaciente).into(fotoUsuariop);
        }
    }

}