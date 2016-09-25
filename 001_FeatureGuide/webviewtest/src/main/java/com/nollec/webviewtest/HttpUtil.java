package com.nollec.webviewtest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nollec on 16-6-30.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL(address);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line=null;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    if(listener!=null){
                    listener.onFinish(response.toString());
                    }

                } catch (Exception e) {
                    if (listener!=null){
                        listener.onError(e);
                    }

                }
                finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();

    }
}
