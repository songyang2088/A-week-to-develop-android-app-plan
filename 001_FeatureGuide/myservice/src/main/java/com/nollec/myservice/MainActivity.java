package com.nollec.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        }
    }
}
