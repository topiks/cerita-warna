package com.tahufikprojects.ceritawarna.artikel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tahufikprojects.ceritawarna.R;

public class DetailArtikelActivity extends AppCompatActivity {
    TextView mJudul, mIsi;
    Bundle bundle;
    String judul, isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        mJudul = findViewById(R.id.judul_artikel_detail);
        mIsi = findViewById(R.id.isi_artikel_detail);

        judul =(String) bundle.get("JUDUL");
        isi =(String) bundle.get("ISI");

        mJudul.setText(judul);
        mIsi.setText(isi);

    }
}