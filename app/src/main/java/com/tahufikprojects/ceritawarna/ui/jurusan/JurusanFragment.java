package com.tahufikprojects.ceritawarna.ui.jurusan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.cari.CariMainActivity;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.deteksiwarna.CapActivity;
import com.tahufikprojects.ceritawarna.tesbutawarna.TestMainActivity;

public class JurusanFragment extends Fragment {

    private JurusanViewModel jurusanViewModel;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jurusanViewModel =
                ViewModelProviders.of(this).get(JurusanViewModel.class);
        View root = inflater.inflate(R.layout.fragment_jurusan, container, false);
//        final TextView textView = root.findViewById(R.id.text_jurusan);
        final Button button = root.findViewById(R.id.button_cari_mulai);
        jurusanViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        button.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick( View v)
                                      {
                                          Intent intent = new Intent();
                                          intent.setClass(getActivity(), CobaCariMainActivity.class);
                                          startActivity(intent);
//                                          toolbar.setBackgroundResource(R.drawable.shape_blue_muda_button);

                                      }
                                  }
        );
        return root;


    }
}