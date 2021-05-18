package com.tahufikprojects.ceritawarna.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.tahufikprojects.ceritawarna.R;

public class ForumMainActivity extends AppCompatActivity {

//    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText editText;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);

//        toolbar = findViewById(R.id.toolbar_forum);
        recyclerView = findViewById(R.id.chat_rv);
        editText = findViewById(R.id.msg_text);
        imageButton = findViewById(R.id.send);
    }
}