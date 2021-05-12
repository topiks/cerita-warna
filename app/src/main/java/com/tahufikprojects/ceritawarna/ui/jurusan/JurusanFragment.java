package com.tahufikprojects.ceritawarna.ui.jurusan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tahufikprojects.ceritawarna.R;

public class JurusanFragment extends Fragment {

    private JurusanViewModel jurusanViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jurusanViewModel =
                ViewModelProviders.of(this).get(JurusanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_jurusan, container, false);
//        final TextView textView = root.findViewById(R.id.text_jurusan);
        jurusanViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}