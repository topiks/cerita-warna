package com.tahufikprojects.ceritawarna.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.artikel.ArtikelMainActivity;
import com.tahufikprojects.ceritawarna.artikel.ArtikelModel;
import com.tahufikprojects.ceritawarna.artikel.DetailArtikelActivity;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.cobacari.ViewHolder;
import com.tahufikprojects.ceritawarna.utils.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ForumMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    ImageButton imageButton;
    Preferences preferences;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    LinearLayoutManager mLinearLayoutManager;

    // recycler view
    FirebaseRecyclerAdapter<Pesan, ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Pesan> options;
    ArrayList<String> pesanModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
//        toolbar = findViewById(R.id.toolbar_forum);
        recyclerView = findViewById(R.id.chat_rv);
        editText = findViewById(R.id.msg_text);
        imageButton = findViewById(R.id.send);
        preferences = new Preferences(this);
        pesanModelArrayList = new ArrayList<String>();

        final String nama = preferences.getValues("nama");
        final String username = preferences.getValues("username");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Forum");

        showPesan();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
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

    private void showPesan() {
        options = new FirebaseRecyclerOptions.Builder<Pesan>().setQuery(databaseReference, Pesan.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Pesan, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Pesan model) {
                holder.setDetails(getApplicationContext(), "", "", "forum_saya", "", "", model.getNama(), model.getPesan());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesan_saya, parent, false);
                ViewHolder viewHolder = new ViewHolder(itemView);

                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(ForumMainActivity.this, "halo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(ForumMainActivity.this, "longgg", Toast.LENGTH_SHORT).show();
                    }
                });

                return viewHolder;
            }
        };
        recyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    protected void onStart()
    {
        super.onStart();
        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }

}