package com.tahufikprojects.ceritawarna.ui.forum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.forum.ForumMainActivity;
import com.tahufikprojects.ceritawarna.ui.home.HomeFragment;

public class ForumFragment extends Fragment {

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

    private ForumViewModel forumViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        final Button button = root.findViewById(R.id.button_forum_from_fragment);
        final Toolbar toolbar = root.findViewById(R.id.toolbar);
        mCallback.Update("forum");
        forumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
                                          intent.setClass(getActivity(), ForumMainActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
        return root;
    }
}