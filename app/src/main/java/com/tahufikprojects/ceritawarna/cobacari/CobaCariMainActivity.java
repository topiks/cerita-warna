package com.tahufikprojects.ceritawarna.cobacari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class CobaCariMainActivity extends AppCompatActivity {

    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference, mDatabaseReferenceKampus;
    FirebaseRecyclerAdapter<JurusanModel, ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<JurusanModel> options;
    Button mbutton;
    Button mbuttonPindah;
    EditText editText;
    ArrayList<String> jurusanModelArrayList, kampusModelArrayList;

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
        mDatabaseReferenceKampus = mFirebaseDatabase.getReference("Kampus");

        mbutton = findViewById(R.id.cari_main);
        editText = findViewById(R.id.coba_cari);
        mbuttonPindah = findViewById(R.id.btn_ke_cari_kampus);

        jurusanModelArrayList = new ArrayList<String>();
        kampusModelArrayList = new ArrayList<String>();

        mbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String strValue = editText.getText().toString();

                if(strValue.equals(""))
                {
                    editText.setError("Masukkan Kata Kunci");
                    editText.requestFocus();
                }
//                showData();
                else
                    jurusanModelArrayList.removeAll(jurusanModelArrayList);
                    kampusModelArrayList.removeAll(kampusModelArrayList);
                    firebaseSearch(strValue, mDatabaseReference, "jurusan");
//                firebaseSearch(strValue, mDatabaseReferenceKampus, "kampus");

            }
        });

        mbuttonPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CobaCariMainActivity.this, CariKampusActivity.class);
                startActivity(intent);
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

    private void firebaseSearch(String searchText, DatabaseReference databaseReference, final String filterby)
    {
        String query = searchText.toLowerCase();
        Query firebaseSearchQuery = databaseReference.orderByChild(filterby).startAt(query).endAt(query + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<JurusanModel>().setQuery(firebaseSearchQuery, JurusanModel.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<JurusanModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull JurusanModel model) {
                    holder.setDetails(getApplicationContext(), model.getJurusan(), model.getKampus());
                    jurusanModelArrayList.add(model.getJurusan());
                    kampusModelArrayList.add(model.getKampus());

                }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
                ViewHolder viewHolder = new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String jurusan = reverseArrayList(jurusanModelArrayList).get(position);
                        String kampus = reverseArrayList(kampusModelArrayList).get(position);
//                        Toast.makeText(CobaCariMainActivity.this,  jurusan + " " + kampus, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CobaCariMainActivity.this, DetailsActivity.class);
                        intent.putExtra("JURUSAN", jurusan);
                        intent.putExtra("KAMPUS", kampus);
                        startActivity(intent);

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

    public ArrayList<String> reverseArrayList(ArrayList<String> alist)
    {
        // Arraylist for storing reversed elements
        ArrayList<String> revArrayList = new ArrayList<String>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }

    public void printElements(ArrayList<Integer> alist)
    {
        for (int i = 0; i < alist.size(); i++) {
            System.out.print(alist.get(i) + " ");
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate();
//        return super.onCreateOptionsMenu(menu);
//    }
}