package com.example.vitamindanalyser;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class bodyexposure extends AppCompatActivity {

    Button result;
    private Chronometer chronometer;
    private boolean running;
    private long pauseOff;
    long valueoftime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyexposure);
        result=findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(bodyexposure.this , Result.class);
                startActivity(in);
            }
        });
        chronometer=findViewById(R.id.chronometer);
    }
    public void startChronometer(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOff);
            chronometer.start();
            running=true;
        }
    }
    public void pauseChronometer(View v){
        if(running){

            chronometer.stop();
            pauseOff=SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;
        }

    }
    public void resetChronometer(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        valueoftime=valueoftime+(pauseOff/1000);
        Toast.makeText(this, "Time stayed in sun = "+valueoftime+"s", Toast.LENGTH_SHORT).show();
        pauseOff=0;
    }
    public void changeFragment(View view){
        Fragment fragment;
        if(view == findViewById(R.id.head)){
            fragment = new head();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.fragment_p, fragment);
            ft.commit();
        }
        if(view==findViewById(R.id.arm)){
            fragment =new torso();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.fragment_p, fragment);
            ft.commit();
        }
        if(view==findViewById(R.id.leg)){
            fragment =new leg();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.fragment_p, fragment);
            ft.commit();
        }
        if(view==findViewById(R.id.feet)){
            fragment =new feet();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.fragment_p, fragment);
            ft.commit();
        }
    }

}