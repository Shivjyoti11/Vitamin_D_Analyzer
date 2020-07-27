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

public class leg extends Fragment {
    RadioGroup radiogroup;
    RadioButton radioButton;
    Double be;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leg, container, false);
        radiogroup = view.findViewById(R.id.radiogrp);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("name",no.getText().toString());
        editor.putString("bodyexposed", String.valueOf(be));
        editor.commit();
        return view;
    }
    public void checkButton4(View view){
        int radioId=radiogroup.getCheckedRadioButtonId();
        radioButton= view.findViewById(radioId);
        if (radioId == 1){
            be= Double.valueOf(4);
        }
        if(radioId==2){
            be= Double.valueOf(3);
        }
        if(radioId==3){
            be= Double.valueOf(2);
        }
        if(radioId==4){
            be= Double.valueOf(1);
        }
        Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
    }
}