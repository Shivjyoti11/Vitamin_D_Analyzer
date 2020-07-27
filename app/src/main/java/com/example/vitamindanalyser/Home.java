package com.example.vitamindanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nutrient=findViewById(R.id.nutrients);
        myDialog=new Dialog(this);
        nam=findViewById(R.id.name);
        progressBar=findViewById(R.id.progressBar);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String n = sharedPreferences.getString("name",DEFAULT);
        ref= FirebaseDatabase.getInstance().getReference().child("Member").child(n);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("name").getValue().toString();
                nam.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Resources resources = getResources();
        Rect bounds = progressBar.getProgressDrawable().getBounds();
        vdd= Double.valueOf(10);
        percent= (int) ((vdd/200)*100);
        if(percent<25) {
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


        result=findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Home.this , Result.class);
                startActivity(in);
            }
        });
        start=findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this , bodyexposure.class);
                startActivity(i);
            }
        });
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent in = new Intent(Home.this , MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
    public void ShowPopUp(View view){
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
        Intent in = new Intent(Home.this , Nutrients.class);
        startActivity(in);
    }
}