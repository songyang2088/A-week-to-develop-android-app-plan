package com.nollec.smsmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView sender;
    private TextView content;
    private IntentFilter receiverFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);
        receiverFilter = new IntentFilter();
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVER");



    }
    class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            Object[] pdus= (Object[]) bundle.get("pdus");//提取短信
            SmsMessage[] messages=new SmsMessage[pdus.length];
            for(int i=0;i<messages.length;i++){
                messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
            }
            String address=messages[0].getOriginatingAddress();//获取发送方号码
            String fullMessage="";
            for(SmsMessage message: messages){
                fullMessage+=message.getMessageBody();//获取短信内容
            }
            sender.setText(address);
            content.setText(fullMessage);


        }
    }
    class SmsReceiver extends  BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            Object[] puds= (Object[]) bundle.get("puds");
            for(Object object: puds){
                SmsMessage message=SmsMessage.createFromPdu((byte[])object);
                Date date=new Date(message.getTimestampMillis());//时间
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-mm-SS");
                String receiverTime=format.format(date);
                String address=message.getOriginatingAddress();
            }
        }
    }
}
