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


public class CrearPerfilFragment extends Fragment implements View.OnClickListener   {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etNombre;
    private EditText etEdad;
    private Button btnActualizar;

    public CrearPerfilFragment() {
    }

    public CrearPerfilFragment newInstance(String param1, String param2) {
        CrearPerfilFragment fragment = new CrearPerfilFragment();
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
        this.etNombre = (EditText) rootView.findViewById(R.id.etNombre);
        this.etEdad = (EditText) rootView.findViewById(R.id.etEdad);
        this.btnActualizar = (Button) rootView.findViewById(R.id.btnActualizar);
        this.btnActualizar.setOnClickListener(this);
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear_perfil, container, false);
    }

    public void onClick(View v) {
        if(v.getId()==this.btnActualizar.getId()){
            Toast.makeText(getContext(),"Perfil creado", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick

}
