package com.nollec.myservice;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.example.myservice.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
=======
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
>>>>>>> e6f4ad129e697f8250d2c5c6acb69c625aa5827e

public class MainActivity extends AppCompatActivity   {

    private Button startService;
    private Button stopService;
    private Button bindServier;
    private Button unbindService;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection  connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder)service;//向下转型

            downloadBinder.startDownload();
            downloadBinder.getProgress();


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        Volley volley=new Volley();
    }
    private void parseXMLWithPull(String xmlData){
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType=xmlPullParser.getEventType();
            String id=null;
            String name=null;
            String version=null;
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("id".equals(nodeName)){
                            id=xmlPullParser.nextText();
                        }else if ("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }else if ("version".equals(nodeName)){
                            version=xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)){
                            Log.d("MainActivity","id is"+id);
                            Log.d("MainActivity","name is"+name);
                            Log.d("MainActivity","version is"+version);
                        }
                        break;
                    default:
                        break;
                }
                eventType=xmlPullParser.next();

            }
        } catch (Exception e) {
            e.printStackTrace();
=======
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindServier= (Button) findViewById(R.id.bind_service);
        unbindService= (Button) findViewById(R.id.unbind_service);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent=new Intent(this,MyService.class);
                startService(startIntent);//启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent=new Intent(this,MyService.class);
                stopService(stopIntent);//停止服务
                break;
            case R.id.bind_service:
                Intent bindIntent=new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                //BIND_AUTO_CREATE 表示在活动和服务进行绑定后自动创建服务
                break;
            case R.id.unbind_service:
                unbindService(connection);

            default:
                break;
>>>>>>> e6f4ad129e697f8250d2c5c6acb69c625aa5827e
        }
    }
}
