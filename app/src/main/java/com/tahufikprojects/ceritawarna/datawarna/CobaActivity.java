package com.tahufikprojects.ceritawarna.datawarna;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tahufikprojects.ceritawarna.R;

public class CobaActivity extends AppCompatActivity {

    Button mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba2);

        mbutton =findViewById(R.id.btn_cek_warna);




        mbutton.setOnClickListener(new View.OnClickListener()
        {
            String hex = "#0C8990";
            String warna = ColorNameBuilder.getColorName(hex);
            float r = Color.red(Color.parseColor(hex)) ;
            int arr[] = ColorNameBuilder.getHSLFromRGB(hex);
            int h = arr[0];
            int s = arr[1];
            int l = arr[2];
            String warna3 = String.valueOf(h);
            String warna4 = String.valueOf(s);
            String warna5 = String.valueOf(l);
            String red = String.valueOf(r);
            String tampilkan = h + " " + s + " " + l + " " + r;

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), warna, Toast.LENGTH_SHORT).show();
            }
        });

    }
}