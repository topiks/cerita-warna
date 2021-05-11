package com.tahufikprojects.ceritawarna.ambilwarna;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tahufikprojects.ceritawarna.R;

public class WarnaActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mTextView;
    View mColorView;
    Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warna);

        mColorView = findViewById(R.id.view_hasil);
        mTextView = findViewById(R.id.text_hasil);
        mImageView = findViewById(R.id.image_color);

        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);

        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
                {
                    bitmap = mImageView.getDrawingCache();

                    int pixel = bitmap.getPixel((int)motionEvent.getX(), (int)motionEvent.getY());

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    String hex = "#" + Integer.toHexString(pixel);

                    mColorView.setBackgroundColor(Color.rgb(r,g,b));
//                    mTextView.setText("RGB r " + r + " g " + g + " b " + b + " HEX " + hex + " pixel " + motionEvent.getX() + " dan " + motionEvent.getY() + "di pixel asli" + pixel);
                    mTextView.setText(" bitmap " + bitmap.getWidth() + " " + bitmap.getHeight() + " image " + mImageView.getWidth() + " " + mImageView.getHeight());
                }
                return true;
            }
        });
    }
}