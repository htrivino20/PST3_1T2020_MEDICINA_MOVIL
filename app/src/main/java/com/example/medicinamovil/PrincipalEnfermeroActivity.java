package com.example.medicinamovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medicinamovil.Fragments.FragmentEnfermeroRecordatorios;
import com.example.medicinamovil.Fragments.FragmentEnfermeroPerfil;
import com.example.medicinamovil.Fragments.FragmentEnfermeroSolicitud;
import com.example.medicinamovil.Fragments.FragmentPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrincipalEnfermeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_enfermero);

        BottomNavigationView bottomNavi = findViewById(R.id.bottom_navigation);
        bottomNavi.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentEnfermeroRecordatorios()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selecFragment = null;

            switch (item.getItemId()){
                case R.id.nav_scheldule:
                    selecFragment = new FragmentEnfermeroRecordatorios();
                    break;
                case R.id.nav_sms:
                    selecFragment = new FragmentEnfermeroSolicitud();
                    break;
                case R.id.nav_profile:
                    selecFragment = new FragmentPerfil();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selecFragment).commit();
            return true;
        }
    };
}