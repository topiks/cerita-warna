package com.tahufikprojects.ceritawarna.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.utils.Preferences;

import java.util.HashMap;
import java.util.Map;

public class ForumMainActivity extends AppCompatActivity {

//    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText editText;
    ImageButton imageButton;
    Preferences preferences;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);

//        toolbar = findViewById(R.id.toolbar_forum);
        recyclerView = findViewById(R.id.chat_rv);
        editText = findViewById(R.id.msg_text);
        imageButton = findViewById(R.id.send);
        preferences = new Preferences(this);

        final String nama = preferences.getValues("nama");
        final String username = preferences.getValues("username");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Forum");


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
//                Toast.makeText(ForumMainActivity.this, nama + " " + msg, Toast.LENGTH_SHORT).show();

//                savePesan(username, nama, msg);
                saveDatabase(username, nama, msg);
            }
        });

    }

    private void saveDatabase(String username, String nama, String contohpesan) {
        Pesan pesan = new Pesan();
        pesan.setNama(nama);
        pesan.setUsername(username);
        pesan.setPesan(contohpesan);

        databaseReference.push().setValue(pesan);
        editText.setText("");
    }

}