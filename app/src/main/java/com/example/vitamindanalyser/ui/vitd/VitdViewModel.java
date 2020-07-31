package com.example.vitamindanalyser.ui.vitd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VitdViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VitdViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Vitamin D fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
