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

public class ActualizarDatosFragment extends Fragment implements View.OnClickListener {

    private EditText etUsername;
    private EditText etContrasena;
    private EditText etNombre;
    private EditText etEdad;
    private Button btnActualizar;

    public ActualizarDatosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public  void init(View rootView){
        this.etUsername = (EditText) rootView.findViewById(R.id.etUsername);
        this.etContrasena = (EditText) rootView.findViewById(R.id.etContrasena);
        this.etNombre = (EditText) rootView.findViewById(R.id.etNombre);
        this.etEdad = (EditText) rootView.findViewById(R.id.etEdad);
        this.btnActualizar = (Button) rootView.findViewById(R.id.btnActualizar);
        this.btnActualizar.setOnClickListener(this);
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_actualizar_datos,container,false);
        init(rootView);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnActualizar.getId()){
            Toast.makeText(getContext(),"Datos actualizados", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick
}
