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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ActualizarDatosFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText etUsername;
    private EditText etContrasena;
    private EditText etNombre;
    private EditText etEdad;
    private Button btnActualizar;

    public ActualizarDatosFragment() {
    }

    public static ActualizarDatosFragment newInstance(String param1, String param2) {
        ActualizarDatosFragment fragment = new ActualizarDatosFragment();
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
        this.btnActualizar = (Button) rootView.findViewById(R.id.btnActualizar);
        this.btnActualizar.setOnClickListener(this);
    }//en init


    private void UpdateUser() {
        final String REGISTER_URL = "http://192.168.10.101:80/TurristeaPHP/?controller=Android&action=actualizar";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", this.etUsername.getText().toString());
        params.put("nombre", this.etNombre.getText().toString());
        params.put("password", this.etContrasena.getText().toString());
        params.put("edad", this.etEdad.getText().toString());

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
