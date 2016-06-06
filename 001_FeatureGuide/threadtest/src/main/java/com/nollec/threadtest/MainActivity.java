package com.nollec.threadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int UPDATE_TEXT=1;
    private TextView textHello;
    private Button changeText;
    private Handler handler=new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    textHello.setText("Change Text");

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textHello = (TextView) findViewById(R.id.text);
        changeText = (Button) findViewById(R.id.changetext);
        changeText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.changetext:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }


    }
}
