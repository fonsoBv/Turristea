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


public class PresupuestoFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etNumPersonas;
    private EditText etCantidadDias;
    private Button btnCalcular;

    public PresupuestoFragment() {
    }

    public PresupuestoFragment newInstance(String param1, String param2) {
        PresupuestoFragment fragment = new PresupuestoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public  void init(View rootView){
        this.etNumPersonas = (EditText) rootView.findViewById(R.id.etNumPersonas);
        this.etCantidadDias = (EditText) rootView.findViewById(R.id.etCantidadDias);
        this.btnCalcular = (Button) rootView.findViewById(R.id.btnCalcular);
        this.btnCalcular.setOnClickListener(this);
    }//en init

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_presupuesto, container, false);
    }

    public void onClick(View v) {
        if(v.getId()==this.btnCalcular.getId()){
            Toast.makeText(getContext(),"Calculando", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick
}
