package com.nollec.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void sendNotifition(View v) {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 创建一个PendingIntent，和Intent类似，不同的是由于不是马上调用，需要在下拉状态条出发的activity，所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Notification notification = new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("TicketText:" + "New messages")
                .setContentText("This is the notification message")
//                .setContentIntent(pendingIntent)
                .setNumber(1).build();
        notification.flags =Notification.FLAG_AUTO_CANCEL;// FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        manager.notify(1,notification);


    }
}
