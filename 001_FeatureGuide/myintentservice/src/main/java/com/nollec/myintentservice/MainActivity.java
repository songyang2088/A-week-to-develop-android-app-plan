package com.nollec.myintentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,MyIntentService.class);
        startService(intent);
        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_intent_service:
                //打印主线程ID
                Log.d("MainActivity","Thread id is"+Thread.currentThread().getId());
                Intent intentService=new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
