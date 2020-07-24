package com.example.vitamindanalyser;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import static android.content.Context.MODE_PRIVATE;

public class head extends Fragment {
RadioGroup radioGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_head, container, false);
        radioGroup=view.findViewById(R.id.rgh);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyData",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("name",no.getText().toString());
        editor.commit();
        return view;
    }
}