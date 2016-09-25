package com.nollec.myintentservice;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
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
        //前台服务
        //当前线程的ID
 /*       Intent notificationIntent= new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        Notification notification=new Notification.Builder(this)
                .setContentTitle("This is a Title")
                .setContentText("this is a text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setNumber(1)
                .setContentIntent(pendingIntent)
                .build();

        notification.flags=Notification.FLAG_NO_CLEAR;
        startForeground(1, notification);
        long time=10000l;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //定时器
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int s=5*1000;
        long triggerAtTime= SystemClock.elapsedRealtime()+s;
        Intent i=new Intent(this,StartReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);

        Log.d("MyIntentService", "Thread is " + Thread.currentThread().getId());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestory executed");
    }
}
