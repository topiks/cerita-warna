package com.tahufikprojects.ceritawarna.ui.artikel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.artikel.ArtikelMainActivity;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;

public class ArtikelFragment extends Fragment {

    private ArtikelViewModel mViewModel;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(ArtikelViewModel.class);
        View root = inflater.inflate(R.layout.artikel_fragment, container, false);

        final Button button  = root.findViewById(R.id.btn_dari_fragment_artikel);

        button.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick( View v)
                                      {
                                          Intent intent = new Intent();
                                          intent.setClass(getActivity(), ArtikelMainActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
        return root;}

}