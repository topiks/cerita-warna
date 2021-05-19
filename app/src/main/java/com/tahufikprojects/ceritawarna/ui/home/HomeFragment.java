package com.tahufikprojects.ceritawarna.ui.home;

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
        int result = Integer.valueOf(score);
        String katakaa;

        if(result <= 9)
            katakaa = "Wah, sayang sekali sepertinya kamu harus memeriksakan mata mu ke dokter";
        else if(result >= 10 && result <= 12)
            katakaa = "Hasil masih tidak dapat dipastikan antara buta warna  parsial atau tidak";
        else
            katakaa = "Dari hasil tes, kemungkinan tidak ada yang salah pada kemampuan mu dalam membedakan warna";

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        View root_header = inflater.inflate(R.layout.app_bar_main, container, false);
         TextView textView = root.findViewById(R.id.nama_pengguna);
         TextView komentar = root.findViewById(R.id.komentar_tes);

        textView.setText(myDataFromActivity);

        if(score == null || score == "0")
        {}
        else
            komentar.setText(katakaa);


        final Button button = root.findViewById(R.id.btn_tes_dari_home);
        final Toolbar toolbar =  root.findViewById(R.id.toolbar);





        button.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick( View v)
                                      {
//                                          Intent intent = new Intent();
//                                          intent.setClass(getActivity(), CapActivity.class);
//                                          startActivity(intent);
//                                          toolbar.setBackgroundResource(R.drawable.shape_blue_muda_button);

//                                          toolbar.setBackgroundColor(Color.rgb(255,0,0));
//                                          button.setBackgroundColor(Color.rgb(255,0,0));

                                      }
                                  }
        );
        return root;
    }
}