package com.example.vitamindanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

public class Result extends AppCompatActivity {

    Button info;
    BarChart barChart;
    GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        info=findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Result.this , Regster2.class);
                startActivity(in);
                finishAfterTransition();
            }
        });
        barChart=findViewById(R.id.bargraph);
        barChart.addBar(new BarModel(200.0f,R.color.colorPrimary));
        barChart.addBar(new BarModel(50.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(150.0f,R.color.white));
        barChart.addBar(new BarModel(20.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(200.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(50.0f,R.color.colorAccent));
        barChart.addBar(new BarModel(100.0f,R.color.colorAccent));
        graphView=findViewById(R.id.pointgraph);
        LineGraphSeries<DataPoint> pointserie=new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1,1),
                new DataPoint(2,4),
                new DataPoint(3,1),
                new DataPoint(4,5),
                new DataPoint(5,4),
                new DataPoint(6,5)
        });
        graphView.addSeries(pointserie);
    }
}