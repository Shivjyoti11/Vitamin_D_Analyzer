package com.example.vitamindanalyser.ui.vitd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.vitamindanalyser.R;
import com.example.vitamindanalyser.ui.gallery.GalleryViewModel;

public class VitdFragment extends Fragment {
    private VitdViewModel VitdViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VitdViewModel =
                ViewModelProviders.of(this).get(VitdViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_vitd, container, false);
        return root;
    }
}
