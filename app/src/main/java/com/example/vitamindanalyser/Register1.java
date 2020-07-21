package com.example.vitamindanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register1 extends AppCompatActivity {

    EditText emid,name,pass,dob,no;
    Button register2;
    FirebaseAuth fAuth;
    DatabaseReference reff;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        emid=findViewById(R.id.emid);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        dob=findViewById(R.id.dob);
        no=findViewById(R.id.no);
        member=new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        fAuth=FirebaseAuth.getInstance();

        register2=(Button) findViewById(R.id.register2);
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pas=pass.getText().toString();
                String em=emid.getText().toString().trim();
                if(name.length()==0){
                    Toast.makeText(Register1.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if(!name.getText().toString().matches("[a-z,A-Z, ]*")){
                    name.setError("Enter character only");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emid.getText().toString()).matches()){
                    emid.setError("Enter valid Email ID");
                }
                else if(pas.length()<6){
                    pass.setError("Characters should be greater than 6");
                }
                else if(!no.getText().toString().matches("[0-9]{10}")){
                    no.setError("Enter 10 digit number");
                }
                else{
                    fAuth.createUserWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register1.this, "User created Success!!", Toast.LENGTH_SHORT).show();
                                int dateof=Integer.parseInt(dob.getText().toString().trim());
                                Long phn=Long.parseLong(no.getText().toString().trim());
                                member.setName(name.getText().toString().trim());
                                member.setDate(dateof);
                                member.setNo(phn);
                                reff.push().setValue(member);
                                Toast.makeText(Register1.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                Intent i =new Intent(Register1.this , Regster2.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(Register1.this, "Error!!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}