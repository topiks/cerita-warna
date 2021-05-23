package com.tahufikprojects.ceritawarna.ui.tes;

import android.app.Activity;
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
import com.tahufikprojects.ceritawarna.tesbutawarna.TestMainActivity;
import com.tahufikprojects.ceritawarna.ui.home.HomeFragment;

public class TesFragment extends Fragment {

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

    private TesViewModel mViewModel;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(TesViewModel.class);
        View root = inflater.inflate(R.layout.tes_fragment, container, false);
//        final TextView textView = root.findViewById(R.id.text_tes);
        final Button button = root.findViewById(R.id.btn_tes);
        mCallback.Update("tes");
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
                                          intent.setClass(getActivity(), TestMainActivity.class);
                                          startActivity(intent);
//                                          toolbar.setBackgroundResource(R.drawable.shape_blue_muda_button);

                                      }
                                  }
        );
        return root;
    }

}