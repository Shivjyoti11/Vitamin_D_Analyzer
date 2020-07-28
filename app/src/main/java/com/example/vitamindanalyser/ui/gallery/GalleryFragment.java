package com.example.vitamindanalyser.ui.gallery;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vitamindanalyser.Home;
import com.example.vitamindanalyser.MainActivity;
import com.example.vitamindanalyser.Nutrients;
import com.example.vitamindanalyser.R;
import com.example.vitamindanalyser.Result;
import com.example.vitamindanalyser.bodyexposure;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class GalleryFragment extends Fragment {

    Button logout;
    Button start;
    Button result;
    Button nutrient;
    Dialog myDialog;
    TextView nam;
    ProgressBar progressBar;
    public static final String DEFAULT = "N/A";
    DatabaseReference ref;
    Double vdd;
    int percent;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        nutrient=root.findViewById(R.id.nutrients);
        myDialog=new Dialog(getActivity());
        nam=root.findViewById(R.id.name);
        progressBar=root.findViewById(R.id.progressBar);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        String n = sharedPreferences.getString("name",DEFAULT);
        ref= FirebaseDatabase.getInstance().getReference().child("Member").child(n);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                nam.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Resources resources = getResources();
        Rect bounds = progressBar.getProgressDrawable().getBounds();
        vdd= Double.valueOf(150);
        percent= (int) ((vdd/200)*100);
        if(percent<30) {
            progressBar.setProgressDrawable(resources.getDrawable(R.drawable.progressbar1));
        }
        else if (percent<75){
            progressBar.setProgressDrawable(resources.getDrawable(R.drawable.progressbar2));
        }
        else {
            progressBar.setProgressDrawable(resources.getDrawable(R.drawable.progressbar));
        }
        progressBar.getProgressDrawable().setBounds(bounds);
        progressBar.setProgress(percent);


        result=root.findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity() , Result.class);
                startActivity(in);
            }
        });
        start=root.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity() , bodyexposure.class);
                startActivity(i);
            }
        });
        logout=root.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
                getActivity().finish();
            }
        });
        nutrient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Nutrients.class);
                startActivity(i);
            }
        });
        return root;
    }
    /*public void ShowPopUp(View view){
        TextView textclose;
        myDialog.setContentView(R.layout.custompopup);
        textclose=(TextView) myDialog.findViewById(R.id.txt);
        textclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
        Intent in = new Intent(getActivity() , Nutrients.class);
        startActivity(in);
    }*/
}