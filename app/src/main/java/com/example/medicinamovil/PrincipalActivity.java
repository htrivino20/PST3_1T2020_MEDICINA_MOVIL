package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medicinamovil.Fragments.FragmentCalendario;
import com.example.medicinamovil.Fragments.FragmentPerfil;
import com.example.medicinamovil.Fragments.FragmentSolicitud;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class PrincipalActivity extends AppCompatActivity {
    public static String idUsuario;
    public static HashMap<String, String[]> usuarios = new HashMap<String, String[]>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        iniciarDatos();
        //Intent intent = getIntent();
        //idUsuario = intent.getStringExtra("usuario");
        //System.out.println("usuario PrincipalActivity: "+idUsuario);

        BottomNavigationView bottomNavi = findViewById(R.id.bottom_navigation);
        bottomNavi.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentCalendario()).commit();

    }

    public static void setUser(String usuario){
        idUsuario=usuario;
    }
    public static String getUser(){
        return idUsuario;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selecFragment = null;
            switch (item.getItemId()){
                case R.id.nav_scheldule:
                    selecFragment = new FragmentCalendario();
                    break;
                case R.id.nav_sms:
                    selecFragment = new FragmentSolicitud();
                    break;
                case R.id.nav_profile:
                    selecFragment = new FragmentPerfil();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selecFragment).commit();
            return true;
        }
    };

    public void iniciarDatos(){
        String[] datos = {"Héctor", "Triviño González","alexander200036@hotmail.com", "0912345678","Terror"};
        usuarios.put("htrivino",datos);
        String[] datos2 = {"Carlos", "Piguave Villamar","cpv1999@hotmail.com", "0912345675","Romántico"};
        usuarios.put("cpv1999",datos2);


        //ProfileFragment.setInfoUser(idUsuario,usuarios.get(idUsuario));
        // El de arriba es el que debe ir, pongo el de abajo para que no tengan que ubicar el nombre del usuario en login
        // Porque si no ponen el usuario se les cae el programa
        //ProfileFragment.setInfoUser("htrivino",usuarios.get("htrivino"));

    }



}