package com.nollec.myintentservice;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

/**
 * Created by nollec on 16-6-17.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //当前线程的ID
        Notification notification=new Notification.Builder(this)
                .setContentTitle("This is a Title")
                .setContentText("this is a text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setNumber(1)
                .build();
        Intent notificationIntent= new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        notification.flags=Notification.FLAG_NO_CLEAR;
        startForeground(1, notification);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour=60*60*1000;
        

        Log.d("MyIntentService","Thread is "+Thread.currentThread().getId());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestory executed");
    }
}
