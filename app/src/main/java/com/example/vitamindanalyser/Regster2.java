package com.example.vitamindanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Regster2 extends AppCompatActivity {

    Button done;
    ImageView type1;
    ImageView type2;
    ImageView type3;
    ImageView type4;
    ImageView type5;
    ImageView type6;
    int st=0;
    EditText wt;
    EditText ht;
    float bmi=0;
    public static final String DEFAULT = "N/A";
    DatabaseReference reff;
    Register register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster2);
        wt=findViewById(R.id.wt);
        ht=findViewById(R.id.ht);
        register=new Register();
        reff=FirebaseDatabase.getInstance().getReference().child("Register");
        type1=findViewById(R.id.type1);
        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Regster2.this, "Very Fair Skin Type", Toast.LENGTH_SHORT).show();
                st=1;
            }
        });
        type2=findViewById(R.id.type2);
        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Regster2.this, "Fair Skin Type", Toast.LENGTH_SHORT).show();
                st=2;
            }
        });
        type3=findViewById(R.id.type3);
        type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Regster2.this, "Medium Skin Type", Toast.LENGTH_SHORT).show();
                st=3;
            }
        });
        type4=findViewById(R.id.type4);
        type4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Regster2.this, "Olive Skin Type", Toast.LENGTH_SHORT).show();
                st=4;
            }
        });
        type5=findViewById(R.id.type5);
        type5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Regster2.this, "Brown Skin Type", Toast.LENGTH_SHORT).show();
                st=5;
            }
        });
        type6=findViewById(R.id.type6);
        type6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Regster2.this, "Black Skin Type", Toast.LENGTH_SHORT).show();
                st=6;
            }
        });
        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (st==0){
                    Toast.makeText(Regster2.this, "Please select your skin type", Toast.LENGTH_SHORT).show();
                }
                else if (wt.length()==0){
                    Toast.makeText(Regster2.this, "Enter your weight", Toast.LENGTH_SHORT).show();
                }
                else if (ht.length()==0){
                    Toast.makeText(Regster2.this, "Enter your height", Toast.LENGTH_SHORT).show();
                }
                else {
                    int sta=st;
                    Float hta=Float.parseFloat(ht.getText().toString().trim());
                    Float wta=Float.parseFloat(wt.getText().toString().trim());
                    bmi=((wta*10000)/(hta*hta));
                    register.setBmi(bmi);
                    register.setHeight(hta);
                    register.setWeight(wta);
                    register.setSkintype(sta);
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                    String n = sharedPreferences.getString("name",DEFAULT);
                    reff.child(n).setValue(register);
                    Toast.makeText(Regster2.this, "Data successfully uploaded BMI="+bmi, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Regster2.this, Home.class);
                    startActivity(in);
                    finish();
                }
            }
        });
    }
}