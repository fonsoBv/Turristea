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

public class OpinionFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnEnviar;

    public OpinionFragment() {
    }//OpinionFragement

    public static OpinionFragment newInstance(String param1, String param2) {
        OpinionFragment fragment = new OpinionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }// OpinionFragment

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }//onCreate

    public void init(View rootView){
        this.btnEnviar = (Button) rootView.findViewById(R.id.btnEnviar);
        this.btnEnviar.setOnClickListener(this);
    }//en init

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opinion, container, false);
    }//onCreateView

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btnEnviar.getId()){
            Toast.makeText(getContext(),"Enviando comentario", Toast.LENGTH_LONG).show();
        }//end if
    }//end onclick

}//end class
