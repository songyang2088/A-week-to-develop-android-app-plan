package com.nollec.myservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by nollec on 16-6-17.
 */
public class MyService extends Service {
    private DownloadBinder mBinder=new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService","startDownload executed");
        }
        public  int getProgress(){
            Log.d("MyService","getProgress executed");
            return 0;
        }
    }
    @Override

    public void onCreate() {
//        前台服务
     Notification notification=new Notification.Builder(this)
             .setContentTitle("This is a Title")
             .setContentText("this is a text")
             .setSmallIcon(R.mipmap.ic_launcher)
             .setNumber(1)
             .build();
        Intent notificationIntent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
//        notification.flags=Notification.FLAG_NO_CLEAR;
        startForeground(1,notification);
        super.onCreate();
        Log.d("MyService","onCreated executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                stopSelf();

            }
        }).start();

        Log.d("MyService","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }


}
