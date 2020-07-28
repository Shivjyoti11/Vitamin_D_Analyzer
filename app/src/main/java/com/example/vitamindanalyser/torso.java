package com.example.vitamindanalyser;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;

public class torso extends Fragment {
    RadioGroup radioGroup;
    RadioButton radioButton,t1,t2,t3,t4,t5;
    Double be= Double.valueOf(0);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_torso, container, false);
        radioGroup = view.findViewById(R.id.radio);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("name",no.getText().toString());
        editor.putString("bodyexposed", String.valueOf(be));
        editor.commit();
        t1=view.findViewById(R.id.t1);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                be=be+ Double.valueOf(4);

                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        t2=view.findViewById(R.id.t2);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);
                be = be+Double.valueOf(3);
            }
        });
        t3=view.findViewById(R.id.t3);
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);

                be= be +Double.valueOf(2);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        t4=view.findViewById(R.id.t4);
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                be=be+ Double.valueOf(1);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        t5=view.findViewById(R.id.t5);
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                be= be+Double.valueOf(1);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}