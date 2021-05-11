package com.tahufikprojects.ceritawarna.ui.jurusan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JurusanViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public JurusanViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Jurusan");
    }

    public LiveData<String> getText() {
        return mText;
    }
}