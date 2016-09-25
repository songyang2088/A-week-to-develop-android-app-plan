package com.nollec.networktest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendRequest;
    private TextView responseText;
    public static final int SHOW_RESPONSE = 0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String respose=(String)msg.obj;
                    responseText.setText(respose);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response);
        sendRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithHttpURLConnection();

        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    // 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                    Log.d("MainActivity","HandlerSendMessage");


                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
