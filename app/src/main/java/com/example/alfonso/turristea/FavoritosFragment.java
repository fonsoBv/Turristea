package com.example.alfonso.turristea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FavoritosFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnSitio1;
    private Button btnSitio2;
    private Button btnSitio3;
    private Button btnSitio4;
    private Button btnSitio5;
    private Button btnSitio6;

    public FavoritosFragment() {
    }

    public static FavoritosFragment newInstance(String param1, String param2) {
        FavoritosFragment fragment = new FavoritosFragment();
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
        this.btnSitio1 = (Button) rootView.findViewById(R.id.btnSitio1);
        this.btnSitio1.setOnClickListener(this);
        this.btnSitio2 = (Button) rootView.findViewById(R.id.btnSitio2);
        this.btnSitio2.setOnClickListener(this);
        this.btnSitio3 = (Button) rootView.findViewById(R.id.btnSitio3);
        this.btnSitio3.setOnClickListener(this);
        this.btnSitio4 = (Button) rootView.findViewById(R.id.btnSitio4);
        this.btnSitio4.setOnClickListener(this);
        this.btnSitio5 = (Button) rootView.findViewById(R.id.btnSitio5);
        this.btnSitio5.setOnClickListener(this);
        this.btnSitio6 = (Button) rootView.findViewById(R.id.btnSitio6);
        this.btnSitio6.setOnClickListener(this);
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnSitio1.getId()){
            Toast.makeText(getContext(),"Redirigiendo a sitio 1...", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick

}
