package com.tahufikprojects.ceritawarna.ui.deteksi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.deteksiwarna.CapActivity;

public class DeteksiFragment extends Fragment {

    private DeteksiViewModel deteksiViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        deteksiViewModel =
                ViewModelProviders.of(this).get(DeteksiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_deteksi, container, false);
        View general_view =  inflater.inflate(R.layout.app_bar_main, container, false);
//        final TextView textView = root.findViewById(R.id.text_deteksi);
        final Button button = root.findViewById(R.id.btn_deteksi);

        final Toolbar toolbar = general_view.findViewById(R.id.toolbar);


        toolbar.setBackgroundColor(Color.rgb(255,0,0));

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
                                          Intent intent = new Intent();
                                          intent.setClass(getActivity(), CapActivity.class);
                                          startActivity(intent);
//                                          toolbar.setBackgroundResource(R.drawable.shape_blue_muda_button);

                                      }
                                  }
        );
        return root;
//

    }
}