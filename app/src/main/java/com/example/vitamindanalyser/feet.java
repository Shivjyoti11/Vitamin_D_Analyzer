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

public class feet extends Fragment {
RadioGroup radioGroup;
RadioButton radioButton,f1,f2,f3;
Double be= Double.valueOf(0);
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feet, container, false);
        radioGroup = view.findViewById(R.id.radiogp);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("name",no.getText().toString());
        editor.putString("bodyexposed", String.valueOf(be));
        editor.commit();
        f1=view.findViewById(R.id.f1);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);
                be=be+ Double.valueOf(2);

                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        f2=view.findViewById(R.id.f2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(radioId);
                be = be+Double.valueOf(1.5);
            }
        });
        f3=view.findViewById(R.id.f3);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton= view.findViewById(radioId);

                be= be+Double.valueOf(0);
                //Toast.makeText(getActivity(),"Selected Value"+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}