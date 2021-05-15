package com.tahufikprojects.ceritawarna.cobacari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.ui.jurusan.JurusanFragment;

import org.w3c.dom.Text;

public class CobaCariMainActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<JurusanModel, ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<JurusanModel> options;
    Button mbutton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_cari_main);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mRecyclerView = findViewById(R.id.hasil_cari);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Jurusan");



        mbutton = findViewById(R.id.cari_main);
        editText = findViewById(R.id.coba_cari);

        mbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String strValue = editText.getText().toString();
                Toast.makeText(CobaCariMainActivity.this, strValue, Toast.LENGTH_SHORT).show();
//                showData();
                firebaseSearch(strValue);
            }
        });
    }

    private void showData() {
        options = new FirebaseRecyclerOptions.Builder<JurusanModel>().setQuery(mDatabaseReference, JurusanModel.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<JurusanModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull JurusanModel model) {
                holder.setDetails(getApplicationContext(), model.getJurusan(), model.getKampus());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(CobaCariMainActivity.this, "halo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(CobaCariMainActivity.this, "long click", Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void firebaseSearch(String searchText)
    {
        String query = searchText.toLowerCase();
        Query firebaseSearchQuery = mDatabaseReference.orderByChild("jurusan").startAt(query).endAt(query + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<JurusanModel>().setQuery(firebaseSearchQuery, JurusanModel.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<JurusanModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull JurusanModel model) {
                holder.setDetails(getApplicationContext(), model.getJurusan(), model.getKampus());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(CobaCariMainActivity.this, "halo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(CobaCariMainActivity.this, "longgg", Toast.LENGTH_SHORT).show();
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate();
//        return super.onCreateOptionsMenu(menu);
//    }
}