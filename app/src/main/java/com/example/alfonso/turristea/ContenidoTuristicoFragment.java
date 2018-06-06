package com.example.alfonso.turristea;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alfonso.turristea.Domain.Sitio;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.HashMap;

public class ContenidoTuristicoFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView tvInfo;
    private TextView tvTituloContenido;
    private ImageView imgImagen;
    private Button btnsiguiente;
    private Button btnatras;
    private int contador;
    ArrayList<Sitio> sitios;

    public ContenidoTuristicoFragment() {
        // Required empty public constructor
    }

    public static ContenidoTuristicoFragment newInstance(String param1, String param2) {
        ContenidoTuristicoFragment fragment = new ContenidoTuristicoFragment();
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

    public void obtenerSitios(){
        final String REGISTER_URL = "http://192.168.42.193:80/TurristeaPHP/?controller=Android&action=obtenerRecomendaciones" +
                "&precio=5000&tipo_viaje=negocio";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, REGISTER_URL,new JSONObject(),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("sitios");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    sitios.add(new Sitio(jsonArray.getJSONObject(i).getInt("precio"),jsonArray.getJSONObject(i).getString("clase"),
                                            jsonArray.getJSONObject(i).getString("tipo_viaje"),jsonArray.getJSONObject(i).getString("descripcion"),
                                            jsonArray.getJSONObject(i).getString("titulo"),(float)jsonArray.getJSONObject(i).getInt("latitud"),
                                            (float)jsonArray.getJSONObject(i).getInt("longitud"),jsonArray.getJSONObject(i).getString("imagen")));
                                }//end for
                                contador = sitios.size()-1;
                                System.out.println(contador);
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
    }

    public  void init(View rootView){
        this.tvTituloContenido = (TextView) rootView.findViewById(R.id.etTituloContenido);
        this.tvInfo = (TextView) rootView.findViewById(R.id.tvInfoContenido);
        this.imgImagen = (ImageView) rootView.findViewById(R.id.imgContenido);
        this.btnatras = (Button) rootView.findViewById(R.id.btnAtrasContenido);
        this.btnsiguiente = (Button) rootView.findViewById(R.id.btnSiguienteContenido);
        this.btnatras.setOnClickListener(this);
        this.btnsiguiente.setOnClickListener(this);
        this.sitios = new ArrayList<>();
        obtenerSitios();
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contenido_turistico,container,false);
        init(rootView);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnatras.getId()){
            if(this.contador>0){
                this.contador--;
                System.out.println(this.contador);
                this.tvInfo.setText(this.sitios.get(this.contador).getDescripcion());
                this.tvTituloContenido.setText(this.sitios.get(this.contador).getTitulo());
            }else {
                Toast.makeText(getContext(), "Este es el primer sitio", Toast.LENGTH_LONG);
            }
        }else if(v.getId()==this.btnsiguiente.getId()){
            if(this.contador<this.sitios.size()-1){
                this.contador++;
                System.out.println(this.contador);
                this.tvInfo.setText(this.sitios.get(this.contador).getDescripcion());
                this.tvTituloContenido.setText(this.sitios.get(this.contador).getTitulo());
            }else{
                Toast.makeText(getContext(),"Este es el ultimo sitio",Toast.LENGTH_LONG);
            }

        }
    }
}
