package com.tahufikprojects.ceritawarna.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tahufikprojects.ceritawarna.CobaActivity;
import com.tahufikprojects.ceritawarna.tesbutawarna.TestMainActivity;
import com.tahufikprojects.ceritawarna.utils.Preferences;

import com.tahufikprojects.ceritawarna.R;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView textView;
    Preferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CobaActivity activity = (CobaActivity) getActivity();
        String myDataFromActivity = activity.getMyData();

        preferences = new Preferences(container.getContext());
        String score = preferences.getValues("score");
        String katakaa;

        if(score.equals("kosong") || score.equals(""))
        {
            katakaa = "Sepertinya anda belum melakukan tes sama sekali";
        }
        else
        {
            int result = Integer.valueOf(score);
            if(result <= 9)
                katakaa = "Wah, sayang sekali sepertinya kamu harus memeriksakan mata mu ke dokter";
            else if(result >= 10 && result <= 12)
                katakaa = "Hasil masih tidak dapat dipastikan antara buta warna  parsial atau tidak";
            else
                katakaa = "Dari hasil tes, kemungkinan tidak ada yang salah pada kemampuan mu dalam membedakan warna";
        }

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        View root_header = inflater.inflate(R.layout.app_bar_main, container, false);
        TextView textView = root.findViewById(R.id.nama_pengguna);
        TextView komentar = root.findViewById(R.id.komentar_tes);

        textView.setText(myDataFromActivity);

        komentar.setText(katakaa);


        final Button button = root.findViewById(R.id.btn_tes_dari_home);
        final Toolbar toolbar =  root.findViewById(R.id.toolbar);





        button.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick( View v)
                                      {
                                          Intent intent = new Intent();
                                          intent.setClass(getActivity(), TestMainActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
        return root;
    }
}