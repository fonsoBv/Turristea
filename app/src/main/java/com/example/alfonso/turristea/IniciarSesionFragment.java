package com.example.alfonso.turristea;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IniciarSesionFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etUsername;
    private EditText etContrasena;
    private EditText etNombre;
    private EditText etEdad;
    private Button btnSesionOlvido;
    private Button btnIniciarSesion;


    public IniciarSesionFragment() {
    }

    public static IniciarSesionFragment newInstance(String param1, String param2) {
        IniciarSesionFragment fragment = new IniciarSesionFragment();
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

    public void init(View rootView){
        this.etUsername = (EditText) rootView.findViewById(R.id.etUsername);
        this.etContrasena = (EditText) rootView.findViewById(R.id.etContrasena);
        this.etNombre = (EditText) rootView.findViewById(R.id.etNombre);
        this.etEdad = (EditText) rootView.findViewById(R.id.etEdad);
        this.btnSesionOlvido = (Button) rootView.findViewById(R.id.btnSesionOlvido);
        this.btnSesionOlvido.setOnClickListener(this);
        this.btnIniciarSesion = (Button) rootView.findViewById(R.id.btnIniciarSesion);
        this.btnIniciarSesion.setOnClickListener(this);
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnIniciarSesion.getId()){
            Toast.makeText(getContext(),"Iniciando sesión", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick

}
