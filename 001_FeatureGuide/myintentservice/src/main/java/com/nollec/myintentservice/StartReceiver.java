package com.nollec.myintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nollec on 16-6-20.
 */
public class StartReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context,MyIntentService.class);
        context.startService(i);
    }
}
