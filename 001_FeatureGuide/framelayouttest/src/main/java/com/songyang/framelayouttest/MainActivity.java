package com.songyang.framelayouttest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private int currentColor = 0;
    final int[] colors = new int[]{
            R.color.colorRed,
            R.color.colorPrimary,
            R.color.colorAccent,
            R.color.colorYellow,
            R.color.colorCyan,
            R.color.colorGreen
    };
    final int[] names = new int[]{
            R.id.tv01,
            R.id.tv02,
            R.id.tv03,
            R.id.tv04,
            R.id.tv05,
            R.id.tv06
    };
    TextView[] textViews = new TextView[names.length];
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 123) {
                for (int i = 0; i < names.length; i++) {
                    textViews[i].setBackgroundResource(colors[(i + currentColor)% names.length]);

                }
                currentColor++;

            }
//            super.handleMessage(msg);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < names.length; i++) {
            textViews[i] = (TextView) findViewById(names[i]);
        }
        //定时改变颜色
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(123);

            }
        }, 0, 300);


    }
}
