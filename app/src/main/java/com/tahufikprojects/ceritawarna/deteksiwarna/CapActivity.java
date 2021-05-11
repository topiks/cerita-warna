package com.tahufikprojects.ceritawarna.deteksiwarna;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tahufikprojects.ceritawarna.R;
import com.tahufikprojects.ceritawarna.datawarna.ColorNameBuilder;

public class CapActivity extends AppCompatActivity {
    // initialize variable
    ImageView imageView;
    Button btOpen;
    TextView namaWarna;
    View colorView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap);

        // assign variable
        imageView = findViewById(R.id.img_src);
        btOpen = findViewById(R.id.open_cam);
        colorView = findViewById(R.id.view_color);
        namaWarna = findViewById(R.id.nama_warna);

        // permission to make cache
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        // request camera permission
        if(ContextCompat.checkSelfPermission(CapActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(CapActivity.this,
                    new String[]
                            {
                                    Manifest.permission.CAMERA
                            }, 100);
        }

        btOpen.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View view)
            {
                // open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
                {
                    bitmap = imageView.getDrawingCache();

                    int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    String hexa = convertRGBtoHex(r, g, b);

                    String color_name = ColorNameBuilder.getColorName(hexa);
                    colorView.setBackgroundColor(Color.rgb(r,g,b));

                    namaWarna.setText(color_name);
                    if(r + g + b >= 600)
                    {
                        namaWarna.setTextColor(Color.BLACK);
                    }
                    else
                    {
                        namaWarna.setTextColor(Color.WHITE);
                    }

                }
                return true;
            }
        });
    }

    static String decToHexa(int n)
    {
        // char array to store hexadecimal number
        char []hexaDeciNum = new char[2];

        // counter for hexadecimal number array
        int i = 0;
        while (n != 0) {

            // temporary variable to store remainder
            int temp = 0;

            // storing remainder in temp variable.
            temp = n % 16;

            // check if temp < 10
            if (temp < 10) {
                hexaDeciNum[i] = (char) (temp + 48);
                i++;
            }
            else {
                hexaDeciNum[i] = (char) (temp + 55);
                i++;
            }

            n = n / 16;
        }

        String hexCode = "";
        if (i == 2) {
            hexCode+=hexaDeciNum[0];
            hexCode+=hexaDeciNum[1];
        }
        else if (i == 1) {
            hexCode = "0";
            hexCode+=hexaDeciNum[0];
        }
        else if (i == 0)
            hexCode = "00";

        // Return the equivalent
        // hexadecimal color code
        return hexCode;
    }

    static String convertRGBtoHex(int R, int G, int B)
    {
        if ((R >= 0 && R <= 255)
                && (G >= 0 && G <= 255)
                && (B >= 0 && B <= 255)) {

            String hexCode = "#";
            hexCode += decToHexa(R);
            hexCode += decToHexa(G);
            hexCode += decToHexa(B);

            return hexCode;
        }

        // The hex color code doesn't exist
        else
            return "-1";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == 100)
        {
            // get capture image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            // set capture image to imgview
            imageView.setImageBitmap(captureImage);
        }
    }


}