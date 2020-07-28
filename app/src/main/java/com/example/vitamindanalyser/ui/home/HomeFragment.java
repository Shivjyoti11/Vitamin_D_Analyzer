package com.example.vitamindanalyser.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vitamindanalyser.R;
import com.example.vitamindanalyser.Regster2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    Button info;
    BarChart barChart;
    GraphView graphView;
    TextView result;
    long time;
    int bsa;
    int st;
    int age;
    int uvi;
    float agf=0;
    float stf;
    float vddose;
    float vdday;
    public static final String DEFAULT = "N/A";
    DatabaseReference reff;
    int date,tm;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        result=root.findViewById(R.id.result);
        time=10;
        bsa=10;
        uvi=1;
        /*age=66;
        st=2;*/
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        String n = sharedPreferences.getString("name",DEFAULT);
        reff= FirebaseDatabase.getInstance().getReference().child("Member").child(n);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                age= Integer.parseInt(snapshot.child("date").getValue().toString());
                if(age<22){
                    agf=1;
                }
                else if(age<41){
                    agf= (float) 0.83;
                }
                else if(age<60){
                    agf= (float) 0.66;
                }
                else{
                    agf= (float) 0.49;
                }
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
                String n = sharedPreferences.getString("name",DEFAULT);
                reff= FirebaseDatabase.getInstance().getReference().child("Register").child(n);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        st= Integer.parseInt(snapshot.child("skintype").getValue().toString());
                        if(st==1){
                            stf=1;
                        }
                        else if(st==2){
                            stf= (float) 1.5;
                        }
                        else if(st==3){
                            stf=2;
                        }
                        else if(st==4){
                            stf=3;
                        }
                        else if(st==5){
                            stf=5;
                        }
                        else if(st==6){
                            stf=8;
                        }
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
                        String n = sharedPreferences.getString("name",DEFAULT);
                        reff= FirebaseDatabase.getInstance().getReference().child("Time").child(n);
                        reff.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                tm= Integer.parseInt(snapshot.child("timer").getValue().toString());
                                vddose= (float) (uvi*2.41);
                                vdday=(vddose*agf*stf*time*bsa*4861)/10200;
                                Toast.makeText(getActivity(), "stf="+stf+"agf="+agf+"vddose="+vddose+"vdday="+vdday, Toast.LENGTH_SHORT).show();
                                result.setText(String.valueOf(vdday));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        info=root.findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity() , Regster2.class);
                startActivity(in);
                getActivity().finishAfterTransition();
            }
        });
        barChart=root.findViewById(R.id.bargraph);
        barChart.addBar(new BarModel(200.0f,R.color.colorPrimary));
        barChart.addBar(new BarModel(50.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(150.0f,R.color.white));
        barChart.addBar(new BarModel(20.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(200.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(50.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(100.0f,R.color.colorAccent));
        graphView=root.findViewById(R.id.pointgraph);
        LineGraphSeries<DataPoint> pointserie=new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1,1),
                new DataPoint(2,4),
                new DataPoint(3,1),
                new DataPoint(4,5),
                new DataPoint(5,4),
                new DataPoint(6,5)
        });
        graphView.addSeries(pointserie);
        return root;
    }
}