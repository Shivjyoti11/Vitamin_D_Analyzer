package com.example.vitamindanalyser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;
import static com.example.vitamindanalyser.Result.DEFAULT;

public class head extends Fragment {
RadioGroup radioGroup;
RadioButton head,cap,cowboy,benie,radioButton;
Double beh= Double.valueOf(0);
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_head, container, false);
        radioGroup=view.findViewById(R.id.rgh);
        beh= Double.valueOf(0);

        head=view.findViewById(R.id.head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                    beh=Double.valueOf(4);

                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        benie=view.findViewById(R.id.beanie);
        benie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);
                beh=Double.valueOf(3);
            }
        });
        cap=view.findViewById(R.id.cap);
        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);

                beh=Double.valueOf(2);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        cowboy=view.findViewById(R.id.cowboy);
        cowboy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                    beh=Double.valueOf(1);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyDat",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("name",no.getText().toString());
        editor.putString("bodyexposedhead", String.valueOf(beh));
        editor.commit();
        return view;
    }
}