package com.example.songy.timeouttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Tony Shen
 *
 */
public class MainActivity extends Activity {

    private Button button;

    private Timer timer;
    private final int TIMER_EXECUTE = 1;

    private final int ERROR_MESSAGE = 1;
    private final int CHECK_TIME = 5000;
    private EThread eThread;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //timer for check the thread
                timer = new Timer();
                timer.schedule(new TimerTask(){
                    @Override
                    public void run() {
                        checkThread();
                    }
                },CHECK_TIME);

                eThread = new EThread();
                eThread.start();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new AlertDialog.Builder(this).setTitle("错误")
                .setMessage("线程超时！").create();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what){

                case TIMER_EXECUTE:
                    if (eThread.getState().toString().equals("TERMINATED")||
                            eThread.getState().toString().equals("TIMED_WAITING")) {
                        eThread.stopThread(true);
                        showDialog(ERROR_MESSAGE);
                        timer.cancel();// 关闭计时器
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    protected void checkThread() {
        Message msg = new Message();
        msg.what = TIMER_EXECUTE;
        mHandler.sendMessage(msg);
    }

    class EThread extends Thread {
        private boolean flag = true;

        public void stopThread(boolean flag) {
            this.flag = !flag;
        }

        @Override
        public void run() {
            Looper.prepare();

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer.cancel();// 关闭计时器
            if(!flag) {
                return;
            }
            Intent i = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(i);
        }
    }
}
