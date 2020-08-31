package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.medicinamovil.Fragments.FragmentPacienteCalendario;
import com.example.medicinamovil.Fragments.FragmentPerfil;
import com.example.medicinamovil.Fragments.FragmentPacienteSolicitud;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrincipalPacienteActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.err.println("ANTES");
        tv=findViewById(R.id.tvVacio);

        //Iniciar la base de datos en el nodo requerido

        setContentView(R.layout.activity_principal);
        BottomNavigationView bottomNavi = findViewById(R.id.bottom_navigation);
        bottomNavi.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentPacienteCalendario()).commit();

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
                    selecFragment = new FragmentPerfil();
            }

            Intent i =getIntent();
            String idCedula = i.getStringExtra("idCedulaUsuario");
            Bundle b = new Bundle();
            b.putString("idCedulaUsuario",idCedula);
            selecFragment.setArguments(b);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selecFragment).commit();
            return true;
        }
    };

}