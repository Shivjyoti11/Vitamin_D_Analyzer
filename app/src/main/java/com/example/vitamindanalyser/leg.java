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
import static com.example.vitamindanalyser.Result.DEFAULT;

public class leg extends Fragment {
    RadioGroup radiogroup;
    RadioButton radioButton,l1,l2,l3,l4;
    Double bel= Double.valueOf(0);
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leg, container, false);
        radiogroup = view.findViewById(R.id.radiog);

        l1=view.findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radiogroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                bel=Double.valueOf(80);

                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        l2=view.findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radiogroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);
                bel =Double.valueOf(24);
            }
        });
        l3=view.findViewById(R.id.l3);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radiogroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);

                bel= Double.valueOf(8);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        l4=view.findViewById(R.id.l4);
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radiogroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                bel=Double.valueOf(0);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyDat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("name",no.getText().toString());
        editor.putString("bodyexposedleg", String.valueOf(bel));
        editor.commit();
        return view;
    }
}