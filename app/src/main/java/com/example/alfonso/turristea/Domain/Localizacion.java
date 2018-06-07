package com.example.alfonso.turristea.Domain;

import android.app.Fragment;
import android.location.*;
import android.os.Bundle;
import android.util.Log;

import com.example.alfonso.turristea.ContenidoTuristicoFragment;

public class Localizacion implements LocationListener {
    ContenidoTuristicoFragment fragment;

    public ContenidoTuristicoFragment getFragment() {
        return fragment;
    }

    public void setFragment(ContenidoTuristicoFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onLocationChanged(Location loc) {
        this.fragment.location = loc;
        this.fragment.setLocation(loc);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }//Fin del método onStatusChanged.
}//Fin de la clase Localización.
