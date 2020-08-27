package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medicinamovil.Fragments.FragmentPacienteCalendario;
import com.example.medicinamovil.Fragments.FragmentPacientePerfil;
import com.example.medicinamovil.Fragments.FragmentPacienteSolicitud;
import com.example.medicinamovil.ObjetosNat.Enfermero;
import com.example.medicinamovil.ObjetosNat.Medicina;
import com.example.medicinamovil.ObjetosNat.Paciente;
import com.example.medicinamovil.ObjetosNat.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrincipalPacienteActivity extends AppCompatActivity {
    //public static HashMap<String, String[]> usuarios = new HashMap<String, String[]>();
    private static Usuario user;
    private static HashMap<Integer, Medicina> dataMedicina =new HashMap<>();

    ArrayList<Medicina> medicinas= new ArrayList<>();
    FirebaseDatabase database;
    Medicina medicina;
    TextView tv;
    //String s ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.err.println("ANTES");
        tv=findViewById(R.id.tvVacio);

        agregarMedicamentos();
        //System.err.println("DESPUES: Tamaño de array: "+s);


        setContentView(R.layout.activity_principal);
        iniciarDatos();

        BottomNavigationView bottomNavi = findViewById(R.id.bottom_navigation);
        bottomNavi.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentPacienteCalendario()).commit();
        //CharSequence a=tv.getText();
        //System.out.println("IMPRESION DE A:"+a);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selecFragment = null;

            switch (item.getItemId()){
                case R.id.nav_scheldule:
                    selecFragment = new FragmentPacienteCalendario();
                    break;
                case R.id.nav_sms:
                    selecFragment = new FragmentPacienteSolicitud();
                    break;
                case R.id.nav_profile:
                    selecFragment = new FragmentPacientePerfil();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selecFragment).commit();
            return true;
        }
    };

    public void iniciarDatos(){
        //Veficar usuario
        HashMap<Integer, String[]> receta=new HashMap<>();
        receta.put(1,new String[]{"8 am","Pendiente"});
        receta.put(3,new String[]{"10 am","Entregado"});
        receta.put(4,new String[]{"12 am","Pendiente"});
        user=new Paciente("0123456789", "contraseña",100, receta);

    }

    public void agregarMedicamentos(){
        //Lectura de todas las medicinas
        dataMedicina.put(1,new Medicina(1,"Paracetamol","https://www.pngkit.com/png/full/694-6945463_pastillas-tempra-caja-de-500mg-paracetamol.png","Descripción paracetamol","100mg"));
        dataMedicina.put(2,new Medicina(2, "Ibuprofeno","https://www.moncloa.com/wp-content/uploads/2020/07/ibuprofeno.jpg","Descripción Ibuprofeno","150mg"));
        dataMedicina.put(3,new Medicina(3, "Trileptal","https://colsubsidio.vteximg.com.br/arquivos/ids/157682-1200-1200/7702635718338.jpg?v=637108450484170000","Descripción Trileptal","250mg"));
        dataMedicina.put(4,new Medicina(4,"Acnotin","https://www.bago.com.ec/wp-content/uploads/2020/03/ACNOTIN-adicional.png","Descripción Acnotin","150ml"));
        dataMedicina.put(5,new Medicina(5,"Norlevo","https://www.farma-vazquez.com/13227-large_default/norlevo-15-mg-1-comprimido.jpg","Descripción Norlevo","250ml"));
        dataMedicina.put(6,new Medicina(6,"Contac","https://www.sanborns.com.mx/imagenes-sanborns-ii/1200/7501065060587.jpg","Descripción Contac","150mg"));
        dataMedicina.put(7,new Medicina(7,"Amoxicilina","https://www.zonatattoos.com/i/foro/antibiotico_amoxicilina.JPG","Descripción Amoxicilina","250mg"));
        dataMedicina.put(8,new Medicina(8,"Ciprofloxacin","https://unof.org/wp-content/uploads/2018/09/ciprofloxacino-300x254.png","Descripción Ciprofloxacin","150mg"));
    }

    public static HashMap<Integer, Medicina> getDataMedicina() {
        return dataMedicina;
    }

    public static Usuario getUser() {
        return user;
    }



}