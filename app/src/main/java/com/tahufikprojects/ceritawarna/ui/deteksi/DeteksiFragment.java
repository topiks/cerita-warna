package com.tahufikprojects.ceritawarna.ui.deteksi;

import android.app.Activity;
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
import com.tahufikprojects.ceritawarna.ui.home.HomeFragment;

public class DeteksiFragment extends Fragment {
    HomeFragment.OnCallbackReceived mCallback;

    public interface OnCallbackReceived {
        public void Update(String data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (HomeFragment.OnCallbackReceived) activity;
        } catch (ClassCastException e) {

        }
    }

    private DeteksiViewModel DeteksiFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DeteksiFragment =
                ViewModelProviders.of(this).get(DeteksiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_deteksi, container, false);

        final Button button = root.findViewById(R.id.btn_deteksi);

        mCallback.Update("deteksi");
        DeteksiFragment.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

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
                                      }
                                  }
        );
        return root;
    }
}