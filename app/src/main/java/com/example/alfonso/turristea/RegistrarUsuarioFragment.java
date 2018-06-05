package com.example.alfonso.turristea;


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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alfonso.turristea.Domain.Person;
import com.example.alfonso.turristea.Domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Httpdevast.devazt.networking.HttpClient;
import Httpdevast.devazt.networking.OnHttpRequestComplete;
import Httpdevast.devazt.networking.Response;

public class RegistrarUsuarioFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etRegistrarCorreo;
    private EditText etRegistrarEdad;
    private Spinner etRegistrarGenero;
    private EditText etRegistrarNombre;
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
        this.etRegistrarNombre = (EditText) rootView.findViewById(R.id.etRegistrarNombre);
        this.etRegistrarCorreo = (EditText) rootView.findViewById(R.id.etRegistrarCorreo);
        this.etRegistrarEdad = (EditText) rootView.findViewById(R.id.etRegistrarEdad);
        this.etRegistrarGenero = (Spinner) rootView.findViewById(R.id.registrarGenero);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(getContext(), R.array.generoarray,android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.etRegistrarGenero.setAdapter(spinner_adapter);
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

    private void RegisterUser() {
        final String REGISTER_URL = "http://192.168.10.101:80/TurristeaPHP/?controller=UsuarioAndroid&action=registrarse";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", this.etRegistrarCorreo.getText().toString());
        params.put("nombre", this.etRegistrarNombre.getText().toString());
        params.put("password", this.etRegistrarContrasenia.getText().toString());
        String itemText = (String) etRegistrarGenero.getSelectedItem();
        System.out.println(itemText);
        params.put("genero",itemText);
        params.put("edad", this.etRegistrarEdad.getText().toString());

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL,new JSONObject(params),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                                Toast.makeText(getActivity(),response.toString() , Toast.LENGTH_LONG).show();
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
    }//RegisterUser


    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnRegistrarUsuario.getId()) {
            if(this.etRegistrarCorreo.getText().toString().length()>0 && this.etRegistrarNombre.getText().toString().length()>0 &&
                    this.etRegistrarEdad.getText().toString().length()>0 && this.etRegistrarConfirmarContrasenia.getText().toString().length()>0) {
                if(this.etRegistrarConfirmarContrasenia.getText().toString().equals(this.etRegistrarContrasenia.getText().toString())) {
                    RegisterUser();
                }
            }else{
                Toast.makeText(getContext(),"Llene todos los campos",Toast.LENGTH_LONG);
            }
        }//end validaciones de formulario
    }//end onclick

}//end class
