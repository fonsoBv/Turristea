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

import java.io.StringBufferInputStream;

public class Turristea extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    DrawerLayout drawer;
    public static NavigationView navigationView;
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("nav",navigationView.toString());
        bundle.putSerializable("too",toolbar.toString());
        Fragment f = new IniciarSesionFragment();
        f.setArguments(bundle);
        Turristea.navigationView = navigationView;
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
            f = new ContenidoTuristicoFragment();
            fragmenTransaction = true;
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
        } else if (id == R.id.nav_form_interes) {
            f = new FormularioInteresFragment();
            fragmenTransaction = true;
        }else if (id == R.id.nav_favoritos) {
            f = new FavoritosFragment();
            fragmenTransaction = true;
        }else if(id==R.id.cerrar_sesion){
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().setGroupVisible(R.id.usuario, false);
            navigationView.getMenu().setGroupVisible(R.id.cuenta,true);
        }

        if(fragmenTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,f).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
