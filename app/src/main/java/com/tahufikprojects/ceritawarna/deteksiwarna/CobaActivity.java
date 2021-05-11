package com.tahufikprojects.ceritawarna.deteksiwarna;

import androidx.appcompat.app.AppCompatActivity;
import com.tahufikprojects.ceritawarna.R;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.WindowManager;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.InputStream;

import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_SIMPLEX;
import static org.opencv.imgproc.Imgproc.findContours;

public class CobaActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    Mat mat1, mat2, mat3;
    BaseLoaderCallback baseLoaderCallback;
    Bitmap bitmap;
    ImageView imageView;

    public float posX ;
    public float posY ;
    public int r ;
    public int g ;
    public int b ;
    public int pixel;
    public int coba;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);

        cameraBridgeViewBase = (JavaCameraView)findViewById(R.id.CameraView);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);

        cameraBridgeViewBase.setDrawingCacheEnabled(true);
        cameraBridgeViewBase.buildDrawingCache(true);

        // coba menggunakan gambar
//        imageView = findViewById(R.id.coba_coba);
//        imageView.setDrawingCacheEnabled(true);
//        imageView.buildDrawingCache(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        baseLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                super.onManagerConnected(status);
                System.out.println("halooooo");
                switch (status)
                {
                    case BaseLoaderCallback.SUCCESS:
                        cameraBridgeViewBase.enableView();
                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }

        };


        cameraBridgeViewBase.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
                {
                    bitmap = cameraBridgeViewBase.getDrawingCache();
                    posX = motionEvent.getX();
                    posY = motionEvent.getY();

                    int pixel = bitmap.getPixel((int)posX, (int)posY);

                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    String ha = String.valueOf(pixel);
                    Toast.makeText(getApplicationContext(), ha, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });



//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE)
//                {
//                    bitmap = imageView.getDrawingCache();
//                    posX = motionEvent.getX();
//                    posY = motionEvent.getY();
//
//                    int pixel = bitmap.getPixel((int)posX, (int)posY);
//
//                    int r = Color.red(pixel);
//                    int g = Color.green(pixel);
//                    int b = Color.blue(pixel);
//
//                    String ha = String.valueOf(pixel);
//                    Toast.makeText(getApplicationContext(), ha, Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });


    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        mat1 = inputFrame.rgba();
        mat2 = inputFrame.rgba();
        Core.transpose(mat1, mat1);
        Core.flip(mat1,mat2,1);

        String relativeposx = String.valueOf(posX);
        String relativeposy = String.valueOf(posY);

        final String posx = String.valueOf(cameraBridgeViewBase.getWidth());
        final String posy = String.valueOf(cameraBridgeViewBase.getHeight());

        String tampilkan = posx + " x " + posy ;

        Imgproc.putText(mat2, relativeposy, new Point(300,500), FONT_HERSHEY_SIMPLEX, 1, new Scalar(0,255,0), 4);
        return  mat2;
    }



    @Override
    public void onCameraViewStarted(int width, int height) {
        mat1 = new Mat(width, height, CvType.CV_8UC4);
        mat2 = new Mat(width, height, CvType.CV_8UC4);
        mat3 = new Mat(width, height, CvType.CV_8UC4);
    }


    @Override
    public void onCameraViewStopped() {
        mat1.release();
//        mat2.release();
//        mat3.release();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug())
        {
            Toast.makeText(getApplicationContext(), "aman", Toast.LENGTH_SHORT).show();
        }
        else
        {
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cameraBridgeViewBase != null)
        {
            cameraBridgeViewBase.disableView();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cameraBridgeViewBase != null)
        {
            cameraBridgeViewBase.disableView();
        }
    }

}
