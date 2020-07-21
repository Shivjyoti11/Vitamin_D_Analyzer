package com.example.vitamindanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button logout;
    Button start;
    Button result;
    Button nutrient;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nutrient=findViewById(R.id.nutrients);
        myDialog=new Dialog(this);
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
                finish();
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
    }
}