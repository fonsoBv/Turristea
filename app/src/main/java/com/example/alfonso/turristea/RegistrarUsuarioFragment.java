package com.example.alfonso.turristea;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarUsuarioFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etRegistrarNombreUsuario;
    private EditText etRegistrarContrasenia;
    private EditText etRegistrarConfirmarContrasenia;
    private Button btnRegistrarUsuario;

    public RegistrarUsuarioFragment() {
    }

    public static RegistrarUsuarioFragment newInstance(String param1, String param2) {
        RegistrarUsuarioFragment fragment = new RegistrarUsuarioFragment();
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

    public  void init(View rootView){
        this.etRegistrarConfirmarContrasenia = (EditText) rootView.findViewById(R.id.etRegistrarConfirmarContraseña);
        this.etRegistrarContrasenia = (EditText) rootView.findViewById(R.id.etRegistrarContrasenia);
        this.btnRegistrarUsuario = (Button) rootView.findViewById(R.id.btnRegistrarUsuario);
        this.btnRegistrarUsuario.setOnClickListener(this);
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registrar_usuario,container,false);
        init(rootView);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnRegistrarUsuario.getId()){
            Toast.makeText(getContext(),"Registrando", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick
}//end class
