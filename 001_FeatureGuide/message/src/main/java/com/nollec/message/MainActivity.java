package com.nollec.message;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
/*
* Message发送短信并且监听发送状态
*
*
* */
    private Button send;
    private EditText content;
    private EditText to;
    private SendStatusReceiver sendStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        to = (EditText) findViewById(R.id.to);
        content = (EditText) findViewById(R.id.content);
        send = (Button) findViewById(R.id.send);
        IntentFilter sendFileter=new IntentFilter();
        sendFileter.addAction("SENT_SMS_ACTION");
        sendStatusReceiver = new SendStatusReceiver();
        registerReceiver(sendStatusReceiver,sendFileter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=to.getText().toString();
                String context=content.getText().toString();
                SmsManager manager=SmsManager.getDefault();
                Intent sentIntent=new Intent("SENT_SMS_ACTION");
                PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this,0,sentIntent,0);
                manager.sendTextMessage(number,null,context,pi,null);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sendStatusReceiver);
//        unregisterReceiver(MessageReceiver);
    }

    class SendStatusReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(getResultCode()==RESULT_OK){
                Toast.makeText(context,"发送成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"发送失败",Toast.LENGTH_LONG).show();

            }

        }
    }
    /*class MessageReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            Object[] pdus= (Object[]) bundle.get("pdus");
            SmsMessage[] messages=new SmsMessage[pdus.length];
            for(int i=0;i<messages.length;i++){
                messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
            }
            String address=messages[0].getOriginatingAddress();
            String fullMessage="";
            for(SmsMessage message:messages){
                fullMessage+=message.getMessageBody();
            }
        }
    }*/
}
