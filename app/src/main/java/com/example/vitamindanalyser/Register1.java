package com.example.vitamindanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1); //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3","4","5","6","7","8","9","10","11", "12", "13","14","15","16","17","18","19","20",
                "21", "22", "23","24","25","26","27","28","29","30","31"}; //create an adapter to describe how the items are displayed, adapters are used in several places in android. //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, items);
        //set the spinners adapter to the pr
        dropdown.setAdapter(adapter);
        Spinner dropdown1 = findViewById(R.id.spinner2); //create a list of items for the spinner.
        String[] items1 = new String[]{"1", "2", "3","4","5","6","7","8","9","10","11", "12"}; //create an adapter to describe how the items are displayed, adapters are used in several places in android. //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, items1);
        //set the spinners adapter to the pr
        dropdown1.setAdapter(adapter1);
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
                else if(dob.length()==0){
                    dob.setError("Enter date of birth");
                }
                else{
                    fAuth.createUserWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register1.this, "User created Success!!", Toast.LENGTH_SHORT).show();
                                int dateof=Integer.parseInt(dob.getText().toString().trim());
                                int d=2020-dateof;
                                Long phn=Long.parseLong(no.getText().toString().trim());
                                String n=no.getText().toString().trim();
                                member.setName(name.getText().toString().trim());
                                member.setPassw(pass.getText().toString().trim());
                                member.setDate(d);
                                member.setNo(phn);
                                reff.child(n).setValue(member);
                                SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("name",no.getText().toString());
                                editor.commit();
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