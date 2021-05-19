package com.tahufikprojects.ceritawarna.ui.home;

import android.graphics.Color;
import android.os.Bundle;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        View root_header = inflater.inflate(R.layout.app_bar_main, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        final Button button = root.findViewById(R.id.btn_tes_dari_home);
        final Toolbar toolbar =  root.findViewById(R.id.toolbar);

        textView = root.findViewById(R.id.nama_pengguna);



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