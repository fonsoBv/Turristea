package com.example.alfonso.turristea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alfonso.turristea.Domain.Localizacion;
import com.example.alfonso.turristea.Domain.Sitio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ContenidoTuristicoFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean bandera=false;
    private String tipoviaje;
    private String ubicacion;
    private String dinero ;
    private VideoView videoView;
    private String mParam1;
    private String mParam2;
    private TextView tvInfo;
    private TextView tvTituloContenido;
    private ImageView imgImagen;
    private Button btnsiguiente;
    private Button btnatras;
    private int contador=0;
    ArrayList<Sitio> sitios;
    private Bitmap bitmap;
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private View mView;
    private LocationManager locationManager;
    public Location location;
    private MarkerOptions[] markerOptions;

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
    }//ContenidoTuristicoFragment

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            //locationStart();
        }//Fin del if.
    }//Fin del onCreate.

    private void locationStart() {
        this.locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        Localizacion localizacion = new Localizacion();
        localizacion.setFragment(this);
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) localizacion);
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) localizacion);
    }//Fin del método locationStart el cual se encarcar de localozar nuestra ubicaccion

    public void setLocation(Location loc) {
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            LatLng ubicacion = new LatLng(9.902685, -83.671778);
            if(this.markerOptions[1] == null) {
                this.markerOptions[1] = new MarkerOptions().title("Tu ubicación").position(ubicacion);
                mGoogleMap.addMarker(markerOptions[1]);
            }
        }//Fin del if.
    }//Fin del método setLocation.

    public void obtenerSitios(){
        final String REGISTER_URL;
        //si entro diferectamente a contenido muestre todos los que hay y de lo contratrio es porq los datos vienen del sitio de interes
        //Aclaracion si cae en el esle consulta a euclides esto significa que viene del formulario de intereses
        if(bandera){
            REGISTER_URL = "http://alonsovargasp.hol.es/?controller=Android&action=obtenerSitios";
        }else {
            REGISTER_URL = "http://alonsovargasp.hol.es/?controller=Android&action=obtenerRecomendaciones" +
                    "&precio=" + this.dinero + "&tipo_viaje=" + this.tipoviaje + "&ubicacion=" + this.ubicacion;
        }//end if-else

        //se hace un json request y se establecen los parametros necesarios
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, REGISTER_URL,new JSONObject(),
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            try {

                                //aca resivimos la respuesta json y convertimos este en un JSON array coon una cabezera sitios de identificador para el array
                                JSONArray jsonArray = response.getJSONArray("sitios");
                                //instacionamos el url decoder para decodificar los datos utf-8 con las tildes y caracteres extraños
                                URLDecoder urlDecoder = new URLDecoder();
                                //recorremos el array y vamos rellenando el un arraylist de obetos sitios para ir mostrandolo
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String titulo =  urlDecoder.decode(jsonArray.getJSONObject(i).get("titulo").toString(),"UTF-8");
                                    String descripcion = urlDecoder.decode(jsonArray.getJSONObject(i).get("descripcion").toString(),"UTF-8");
                                    sitios.add(new Sitio(jsonArray.getJSONObject(i).getString("precio"),jsonArray.getJSONObject(i).getString("ubicacion"),
                                            jsonArray.getJSONObject(i).getString("tipo_viaje"), descripcion,
                                           titulo,Double.parseDouble(jsonArray.getJSONObject(i).getString("latitud")),
                                            Double.parseDouble(jsonArray.getJSONObject(i).getString("longitud")),jsonArray.getJSONObject(i).getString("imagen"),
                                            urlDecoder.decode( jsonArray.getJSONObject(i).getString("video"),"UTF-8")));
                                }//end for

                                //este contador se define aqui pero se usa ir pasando sitio por sitio en el onclick listener
                                contador = sitios.size()-1;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
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
    }//metodo para enviar por medio de volley este metodo hace una peticion post y manda los datos en formato json

    private void cargarWebServiceImagen(String urlImagen) {
        urlImagen=urlImagen.replace(" ","%20");

        ImageRequest imageRequest=new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                bitmap=response;
                imgImagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(imageRequest);
    }//este metodo nos carga un textview con la imagen se trae la url de la imagen de la base y realiza un imagereques

    public  void init(View rootView) {
        this.tvTituloContenido = (TextView) rootView.findViewById(R.id.etTituloContenido);
        this.tvInfo = (TextView) rootView.findViewById(R.id.tvInfoContenido);
        this.imgImagen = (ImageView) rootView.findViewById(R.id.imgContenido);
        this.btnatras = (Button) rootView.findViewById(R.id.btnAtrasContenido);
        this.btnsiguiente = (Button) rootView.findViewById(R.id.btnSiguienteContenido);
        this.btnatras.setOnClickListener(this);
        this.imgImagen = (ImageView) rootView.findViewById(R.id.imgContenido);
        this.btnsiguiente.setOnClickListener(this);
        this.sitios = new ArrayList<>();
        this.videoView = (VideoView) rootView.findViewById(R.id.Video);
        Uri uri = Uri.parse("rtsp://r2---sn-q4flrnl7.googlevideo.com/Cj0LENy73wIaNAk4g1DyQx-NvBMYDSANFC2j9RhbMOCoAUIASARg3Nf80_KSzvNaigELU2lTZDF1dnFiT2MM/79A68EB44431EB7BBC48D04DBD75EF9E59878E1A.E4131725CB76DC05ACD9CA5516555A1C2E43E600/yt6/1/video.3gp");
        this.videoView.setMediaController(new MediaController(getContext()));
        this.videoView.setVideoURI(uri);
        this.videoView.requestFocus();
        this.videoView.start();
        this.markerOptions = new MarkerOptions[2];
    }//en init incializa compoentes

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_contenido_turistico, container, false);
        init(mView);
        return mView;
    }//onCreateView

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }//OncreatedView

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if(null == getArguments()){
            bandera=true;
        }else {
            tipoviaje = getArguments().getString("tipoviaje");
            ubicacion = getArguments().getString("ubicacion");
            dinero = getArguments().getString("dinero");
        }
        obtenerSitios();

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng ubicacion = new LatLng(9.970865, -83.690761);
        LatLng ubicacionActual = new LatLng(9.902685, -83.671778);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 9));
        markerOptions[0] = new MarkerOptions().title("Esta es nuestra ubicación").position(ubicacion);
        markerOptions[1] = new MarkerOptions().title("Tu ubicación").position(ubicacionActual);
        mGoogleMap.addMarker(markerOptions[0]);
        mGoogleMap.addMarker(markerOptions[1]);
    }//onMapReady

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnsiguiente.getId()){
            if(this.contador>0){
                this.contador--;
                this.tvInfo.setText(this.sitios.get(this.contador).getDescripcion());
                this.tvTituloContenido.setText(this.sitios.get(this.contador).getTitulo());
                cargarWebServiceImagen("http://" + this.sitios.get(this.contador).getImagen());
                this.mGoogleMap.clear();
                LatLng ubicacion = new LatLng(this.sitios.get(this.contador).getLatitud(), this.sitios.get(this.contador).getLongitud());
                System.out.println(this.sitios.get(this.contador).getLatitud());
                markerOptions[0] = new MarkerOptions().title("Esta es nuestra ubicación").position(ubicacion);
                mGoogleMap.addMarker(markerOptions[0]);
                mGoogleMap.addMarker(markerOptions[1]);
                Uri uri = Uri.parse(this.sitios.get(contador).getVideo());
                this.videoView.setMediaController(new MediaController(getContext()));
                this.videoView.setVideoURI(uri);
                this.videoView.requestFocus();
                this.videoView.start();
            }else {
                Toast.makeText(getContext(), "Este es el primer sitio", Toast.LENGTH_LONG).show();
            }//else-if
        }else if(v.getId()==this.btnatras.getId()){
            if(this.contador<this.sitios.size()-1){
                this.contador++;
                System.out.println(this.contador);
                this.tvInfo.setText(this.sitios.get(this.contador).getDescripcion());
                this.tvTituloContenido.setText(this.sitios.get(this.contador).getTitulo());
                cargarWebServiceImagen("http://" + this.sitios.get(this.contador).getImagen());
                this.mGoogleMap.clear();
                LatLng ubicacion = new LatLng(this.sitios.get(this.contador).getLatitud(), this.sitios.get(this.contador).getLongitud());
                markerOptions[0] = new MarkerOptions().title("Esta es nuestra ubicación").position(ubicacion);
                mGoogleMap.addMarker(markerOptions[0]);
                mGoogleMap.addMarker(markerOptions[1]);

                Uri uri = Uri.parse(this.sitios.get(contador).getVideo());
                this.videoView.setMediaController(new MediaController(getContext()));
                this.videoView.setVideoURI(uri);
                this.videoView.requestFocus();
                this.videoView.start();
            }else{
                Toast.makeText(getContext(),"Este es el ultimo sitio",Toast.LENGTH_LONG).show();
            }//else-if
        }//else-if
    }//end Onclick
}//end class
