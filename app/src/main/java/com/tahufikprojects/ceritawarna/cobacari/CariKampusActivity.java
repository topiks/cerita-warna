package com.tahufikprojects.ceritawarna.cobacari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.google.firebase.database.Query;
import com.tahufikprojects.ceritawarna.R;

public class CariKampusActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<KampusModel, ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<KampusModel> options;
    Button mbutton;
    Button mbuttonPindah;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_kampus);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mRecyclerView = findViewById(R.id.hasil_cari_kampus);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("Kampus");

        mbutton = findViewById(R.id.cari_main_kampus);
        editText = findViewById(R.id.coba_cari_kampus);
        mbuttonPindah = findViewById(R.id.btn_ke_cari_jurusan);

        mbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String strValue = editText.getText().toString();

                if(strValue.equals(""))
                {
                    editText.setError("Masukkan Kata Kunci");
                    editText.requestFocus();
                }

                else
                    firebaseSearch(strValue, mDatabaseReference, "kampus");

            }
        });

        mbuttonPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CariKampusActivity.this, CobaCariMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void firebaseSearch(String searchText, DatabaseReference databaseReference, final String filterby)
    {
        String query = searchText.toLowerCase();
        Query firebaseSearchQuery = databaseReference.orderByChild(filterby).startAt(query).endAt(query + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<KampusModel>().setQuery(firebaseSearchQuery, KampusModel.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<KampusModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull KampusModel model) {
                    holder.setDetails(getApplicationContext(), model.getKampus(), model.getJurusan());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(CariKampusActivity.this, "halo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(CariKampusActivity.this, "longgg", Toast.LENGTH_SHORT).show();
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