package com.tahufikprojects.ceritawarna.ui.deteksi;

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
import com.tahufikprojects.ceritawarna.deteksiwarna.CapActivity;
import com.tahufikprojects.ceritawarna.ui.jurusan.JurusanFragment;

public class DeteksiFragment extends Fragment {

    private DeteksiViewModel deteksiViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        deteksiViewModel =
                ViewModelProviders.of(this).get(DeteksiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_deteksi, container, false);
//        final TextView textView = root.findViewById(R.id.text_deteksi);
        final Button button = root.findViewById(R.id.btn_deteksi);
        deteksiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
//                                          Intent intent = new Intent(DeteksiFragment.this, CapActivity.class);
//                                          DeteksiFragment.startActivity(intent);
                                          Intent intent = new Intent();
                                          intent.setClass(getActivity(), CapActivity.class);
                                          startActivity(intent);

                                      }
                                  }
        );
        return root;
//

    }
}