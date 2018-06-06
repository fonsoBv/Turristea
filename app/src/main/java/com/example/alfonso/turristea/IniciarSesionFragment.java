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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class IniciarSesionFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etUsername;
    private EditText etContrasena;
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
        this.btnSesionOlvido = (Button) rootView.findViewById(R.id.btnSesionOlvido);
        this.btnSesionOlvido.setOnClickListener(this);
        this.btnIniciarSesion = (Button) rootView.findViewById(R.id.btnIniciarSesion);
        this.btnIniciarSesion.setOnClickListener(this);
    }//en init


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_iniciar_sesion,container,false);
        init(rootView);
        return rootView;
    }

    public void iniciarSesion(){
        final String REGISTER_URL = "http://192.168.43.13:80/TurristeaPHP/?controller=Android&action=login";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", this.etUsername.getText().toString());
        params.put("password", this.etContrasena.getText().toString());
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL,new JSONObject(params),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            Toast.makeText(getActivity(),"Iniciando..." , Toast.LENGTH_LONG).show();
                            Fragment fr = new PrincipalFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fr).addToBackStack(null).commit();
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

    }//iniciarSesion


    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnIniciarSesion.getId()){
            if(this.etContrasena.getText().toString().length()>0 && this.etUsername.getText().toString().length()>0) {
                iniciarSesion();
            }else{
                Toast.makeText(getContext(),"LLene todos los campos de textos",Toast.LENGTH_LONG);
            }
        }//end if
    }//end onclick

}
