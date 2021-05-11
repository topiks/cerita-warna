package com.tahufikprojects.ceritawarna.ui.tes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}