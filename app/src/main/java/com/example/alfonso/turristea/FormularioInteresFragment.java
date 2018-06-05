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

    }
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
        View rootView = inflater.inflate(R.layout.fragment_iniciar_sesion,container,false);
        init(rootView);
        return rootView;
    }//onCreateView

    public void init(View root){
        this.etDinero = (EditText) root.findViewById(R.id.etDinero);
        this.etTipoViaje = (Spinner) root.findViewById(R.id.spinnerTipoViaje);
        this.etUbicacion = (Spinner) root.findViewById(R.id.spinnerUbicacion);
        this.btnbuscar = (Button) root.findViewById(R.id.btnBuscarFormulario);
        this.btnbuscar.setOnClickListener(this);
    }//end init

    public void buscar(){
        final String REGISTER_URL = "http://192.168.10.101:80/TurristeaPHP/?controller=UsuarioAndroid&action=login";
        HashMap<String, String> params = new HashMap<String, String>();
        String TipoViaje = (String) etTipoViaje.getSelectedItem();
        String  ubicacion = (String) etUbicacion.getSelectedItem();
        params.put("dinero", this.etDinero.getText().toString());
        params.put("tipoViaje", TipoViaje);
        params.put("ubicacion", ubicacion);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL,new JSONObject(params),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            Toast.makeText(getActivity(),"Iniciando...", Toast.LENGTH_LONG).show();
                            try {
                                JSONObject obj1 = response;
                                JSONArray result = obj1.getJSONArray("resultado");

                            Fragment fr = new ContenidoTuristicoFragment();
                            Bundle args = new Bundle();
                            args.putSerializable("resultado",(Serializable) result);
                            args.putString("email", response.toString());
                            fr.setArguments(args);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fr).addToBackStack(null).commit();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error..", Toast.LENGTH_LONG).show();
                        }//if-else
                    }//onResponse
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }//end buscar

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnbuscar.getId()){
            if(etDinero.getText().toString().length()>0) {
                buscar();
            }//end if
        }//end if
    }//onCLIck
}
