package com.example.alfonso.turristea;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

public class FormularioInteresFragment  extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner etUbicacion;
    private EditText etDinero;
    private Spinner etTipoViaje;
    private Button btnbuscar;

    public FormularioInteresFragment() {

    }//constructor
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IniciarSesionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public FormularioInteresFragment newInstance(String param1, String param2) {
        FormularioInteresFragment fragment = new FormularioInteresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }//FormularioInteresFragment

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }//end if
    }//onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_formulario_interes,container,false);
        init(rootView);
        return rootView;
    }//onCreateView

    public void init(View root){
        this.etDinero = (EditText) root.findViewById(R.id.etDinero);
        this.etTipoViaje = (Spinner) root.findViewById(R.id.spinnerTipoViaje);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipoviajearray,android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.etTipoViaje.setAdapter(spinner_adapter);
        this.etUbicacion = (Spinner) root.findViewById(R.id.spinnerUbicacion);
        ArrayAdapter spinner_adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.ubicacionarray,android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.etUbicacion.setAdapter(spinner_adapter2);
        this.btnbuscar = (Button) root.findViewById(R.id.btnBuscarFormulario);
        this.btnbuscar.setOnClickListener(this);
    }//end init

    public void buscar(){
        String TipoViaje = (String) etTipoViaje.getSelectedItem();
        String  ubicacion = (String) etUbicacion.getSelectedItem();
        float dinero = Float.parseFloat(this.etDinero.getText().toString());
        Fragment fr = new ContenidoTuristicoFragment();
        Bundle args = new Bundle();
        //enviamos las variables del formulario al contenido turistico para hacer el calculo de euclides
        args.putString("tipoviaje",TipoViaje);
        args.putString("ubicacion", ubicacion);
        args.putString("dinero", precioPrueba(dinero));
        fr.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fr).addToBackStack(null).commit();
    }//end buscar

    public String precioPrueba(float precio){
        if(precio>0 && precio<=1000){
            return "100-1000";
        }else if(precio>=1001 && precio<=5000){
            return "1001-5000";
        }
            return "5001-15000";
    }//metodo para cdefinir el rango del precio


    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnbuscar.getId()){
            if(etDinero.getText().toString().length()>0) {
                buscar();
            }else{
                Toast.makeText(getContext(),"Complete todos los datos",Toast.LENGTH_LONG).show();;
            }
        }//end if
    }//onCLIck
}//end class
