package com.tahufikprojects.ceritawarna.ui.deteksi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeteksiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DeteksiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Deteksi");
    }

    public LiveData<String> getText() {
        return mText;
    }
}