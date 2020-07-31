package com.example.vitamindanalyser.ui.bmi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.vitamindanalyser.R;
import com.example.vitamindanalyser.Result;
import com.example.vitamindanalyser.ui.vitd.VitdViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;
public class BmiFragment extends Fragment {
    public static final String DEFAULT = "N/A";
    DatabaseReference reff;
    TextView bi;
    private com.example.vitamindanalyser.ui.bmi.BmiViewModel BmiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BmiViewModel =
                ViewModelProviders.of(this).get(BmiViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_bmi, container, false);
        bi = root.findViewById(R.id.bmivalue);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", MODE_PRIVATE);
        String n = sharedPreferences.getString("name",DEFAULT);
        reff= FirebaseDatabase.getInstance().getReference().child("Register").child(n);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String b="";
                b = snapshot.child("bmi").getValue().toString();
                bi.setText(b);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}
