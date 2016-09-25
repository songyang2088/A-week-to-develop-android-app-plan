package com.example.songy.beautifulgirl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView girl;
    private Bitmap srcBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        girl = (ImageView) findViewById(R.id.tv_pre);
        //把要操作的图片转换成bitmap
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre19);
        //创建原图的副本 模板
        final Bitmap alterBitmap=Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(),srcBitmap.getConfig());
        //创建画布
        Canvas canvas=new Canvas(alterBitmap);
        //画笔
        Paint paint=new Paint();
        canvas.drawBitmap(srcBitmap,new Matrix(),paint);
        girl.setImageBitmap(alterBitmap);
        girl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action=event.getAction();
                switch (action){
                    case MotionEvent.ACTION_MOVE:
                        for(int i=-10;i<10;i++){
                            for(int j=-10;j<10;j++){
                                if(Math.sqrt(i*i+j*j)<10)
                                    try {
                                        alterBitmap.setPixel((int) event.getX()+i,(int) event.getY()+j, Color.TRANSPARENT);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                            }

                        }
                        girl.setImageBitmap(alterBitmap);
                        break;
                }
                return true;
            }

        });


    }
}
