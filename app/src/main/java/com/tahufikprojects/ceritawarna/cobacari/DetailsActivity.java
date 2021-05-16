package com.tahufikprojects.ceritawarna.cobacari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tahufikprojects.ceritawarna.R;

public class DetailsActivity extends AppCompatActivity {
    Bundle bundle;
    String jurusan, kampus;
    TextView tJurusan, tKampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        jurusan =(String) bundle.get("JURUSAN");
        kampus =(String) bundle.get("KAMPUS");

        tJurusan = findViewById(R.id.detail_jurusan);
        tKampus = findViewById(R.id.detail_kampus);

        tJurusan.setText(jurusan);
        tKampus.setText(kampus);


    }
}