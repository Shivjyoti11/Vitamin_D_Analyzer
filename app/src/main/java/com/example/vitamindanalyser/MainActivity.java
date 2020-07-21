package com.example.vitamindanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText pass,em;
    TextView btn;
    Button login;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        em=findViewById(R.id.em);
        pass=findViewById(R.id.pass);
        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
            Intent i = new Intent(MainActivity.this , Home.class);
            startActivity(i);
            finish();
        }
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pas=pass.getText().toString().trim();
                String emid=em.getText().toString().trim();
                if(em.length()==0){
                    Toast.makeText(MainActivity.this, "Enter Email Id", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(em.getText().toString()).matches()){
                    em.setError("Enter valid Email-ID");
                }
                else if(pas.length()<6){
                    pass.setError("Characters should be greater than 6");
                }
                else{
                    fAuth.signInWithEmailAndPassword(emid,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this , Home.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        btn=findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this , Register1.class);
                startActivity(in);
                finish();
            }
        });
    }
}