package com.example.vitamindanalyser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class feet extends Fragment {
RadioGroup radiogroup;
RadioButton radioButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feet, container, false);
        radiogroup = view.findViewById(R.id.radiogrp);
        return view;
    }
}