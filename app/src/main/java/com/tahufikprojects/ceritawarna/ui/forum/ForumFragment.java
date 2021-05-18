package com.tahufikprojects.ceritawarna.ui.forum;

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

public class ForumFragment extends Fragment {

    private ForumViewModel forumViewModel;
    Button mbutton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
//        final TextView textView = root.findViewById(R.id.text_forum);
        final Button button = root.findViewById(R.id.button_forum_from_fragment);
        final Toolbar toolbar = root.findViewById(R.id.toolbar);
        forumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//                toolbar.setBackgroundColor(0);
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
//                                          toolbar.setBackgroundResource(R.drawable.shape_blue_muda_button);

                                      }
                                  }
        );
        return root;
    }
}