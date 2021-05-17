package com.tahufikprojects.ceritawarna.artikel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.cobacari.JurusanModel;
import com.tahufikprojects.ceritawarna.cobacari.ViewHolder;

public class ArtikelMainActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<ArtikelModel, ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<ArtikelModel> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_main);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mRecyclerView = findViewById(R.id.list_artikel);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Artikel");

        showData();
    }


    private void showData() {
        options = new FirebaseRecyclerOptions.Builder<ArtikelModel>().setQuery(mDatabaseReference, ArtikelModel.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ArtikelModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ArtikelModel model) {
                holder.setDetails(getApplicationContext(), "", "", "artikel", model.getJudul(), model.getIsi());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_artikel, parent, false);
                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(ArtikelMainActivity.this, "halo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(ArtikelMainActivity.this, "long click", Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
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