package com.tahufikprojects.ceritawarna.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tahufikprojects.ceritawarna.R;

public class ForumFragment extends Fragment {

    private ForumViewModel forumViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
//        final TextView textView = root.findViewById(R.id.text_forum);
        final Toolbar toolbar = root.findViewById(R.id.toolbar);
        forumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//                toolbar.setBackgroundColor(0);
            }
        });
        return root;
    }
}