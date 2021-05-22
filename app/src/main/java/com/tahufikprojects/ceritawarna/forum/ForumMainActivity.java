package com.tahufikprojects.ceritawarna.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.artikel.ArtikelMainActivity;
import com.tahufikprojects.ceritawarna.artikel.ArtikelModel;
import com.tahufikprojects.ceritawarna.artikel.DetailArtikelActivity;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.cobacari.ViewHolder;
import com.tahufikprojects.ceritawarna.utils.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    String username;
    int src;

    // container chat
    ArrayList<String> usernameArrayList;

    // view type
    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    // scroll view
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(false);
        mLinearLayoutManager.setStackFromEnd(false);

        usernameArrayList = new ArrayList<String>();

        recyclerView = findViewById(R.id.chat_rv);
        editText = findViewById(R.id.msg_text);
        imageButton = findViewById(R.id.send);
        preferences = new Preferences(this);
        pesanModelArrayList = new ArrayList<String>();

        final String nama = preferences.getValues("nama");
        username = preferences.getValues("username");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Forum");

        showPesan();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
                saveDatabase(username, nama, msg);
                simpanArray();
//                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
                recyclerView.smoothScrollToPosition(View.FOCUS_DOWN);
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
        simpanArray();
//        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
        options = new FirebaseRecyclerOptions.Builder<Pesan>().setQuery(databaseReference, Pesan.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Pesan, ViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Pesan model) {

                    if(model.getUsername().equals(username))
                    {
                        src = 0;
                        holder.setDetails(getApplicationContext(), "", "", "forum_saya", "", "", model.getNama(), model.getPesan());
//                        usernameArrayList.add(model.getUsername());
                    }
                    else
                    {
                        src = 1;
                        holder.setDetails(getApplicationContext(), "", "", "forum_kamu", "", "", model.getNama(), model.getPesan());
//                        usernameArrayList.add(model.getUsername());
                    }
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if(viewType == MSG_TYPE_RIGHT)
                {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesan_saya, parent, false);
                    ViewHolder viewHolder = new ViewHolder(view);

                    viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//                            String username = usernameArrayList.get(position);
//                            Toast.makeText(ForumMainActivity.this, username, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {
//                            Toast.makeText(ForumMainActivity.this, fiew, Toast.LENGTH_SHORT).show();
                        }
                    });

                    return viewHolder;
                }

                else
                {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesan_kamu, parent, false);
                    ViewHolder viewHolder = new ViewHolder(view);

                    viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//                            String username = usernameArrayList.get(position);
//                            Toast.makeText(ForumMainActivity.this, username, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {
//                            Toast.makeText(ForumMainActivity.this, fiew, Toast.LENGTH_SHORT).show();
                        }
                    });

                    return viewHolder;
                }

//                    View itemView;
//                    itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesan_saya, parent, false);
//                    ViewHolder viewHolder = new ViewHolder(itemView);
//
//                    final String fiew = String.valueOf(viewType);
//
//                    viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            String username = usernameArrayList.get(position);
//                            Toast.makeText(ForumMainActivity.this, username, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onItemLongClick(View view, int position) {
//                            Toast.makeText(ForumMainActivity.this, fiew, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                    return viewHolder;

            }


            @Override
            public int getItemViewType(int position) {
                if(usernameArrayList.get(position).equals(username))
                    return MSG_TYPE_RIGHT;
                else
                    return MSG_TYPE_LEFT;
            }

        };
        recyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    private void simpanArray() {
        usernameArrayList.removeAll(usernameArrayList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue() != null)
                {
                    for (DataSnapshot snapshot1 : snapshot.getChildren())
                    {
                        String nama = snapshot1.child("username").getValue().toString();
                        usernameArrayList.add(nama);
                        String sz = String.valueOf(usernameArrayList.size());
                        Log.d("WASU", sz);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ForumMainActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
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