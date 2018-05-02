package com.example.alfonso.turristea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Turristea extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turristea);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Turristea");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment f = new PrincipalFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,f).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean fragmenTransaction = false;
        Fragment f = null;
        int id = item.getItemId();
        if (id == R.id.nav_inicio) {
            f = new PrincipalFragment();
            fragmenTransaction = true;
        } else if (id == R.id.nav_contenido) {

        } else if (id == R.id.nav_info) {
            f = new InformacionFragment();
            fragmenTransaction = true;
        } else if (id == R.id.nav_opinion) {
            f=new OpinionFragment();
            fragmenTransaction = true;
        } else if (id == R.id.nav_registrar) {
            f= new RegistrarUsuarioFragment();
            fragmenTransaction = true;
        } else if (id == R.id.nav_sesion) {
            f = new IniciarSesionFragment();
            fragmenTransaction = true;
        } /*else if (id == R.id.nav_form_interes) {
            f = new FormularioInteresFragment();
            fragmenTransaction = true;
        }else if (id == R.id.nav_ver_perfil) {
            f = new VerPerfilFragment();
            fragmenTransaction = true;
        }else if (id == R.id.nav_presupuesto) {
            f = new PresupuestoFragment();
            fragmenTransaction = true;
        }else if (id == R.id.nav_crear_perfil) {
            f = new CrearPerfilFragment();
            fragmenTransaction = true;
        }*/




        if(fragmenTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,f).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
