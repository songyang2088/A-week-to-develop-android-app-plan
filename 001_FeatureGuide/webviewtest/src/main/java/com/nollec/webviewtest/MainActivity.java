package com.nollec.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String
                    url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求,不用借助系统浏览器

            }
        });
        webView.loadUrl("http://www.baidu.com");
        HttpUtil httpUtil=new HttpUtil();
        httpUtil.sendHttpRequest("http://www.baidu.com", new HttpCallBackListener() {
            @Override
            //对从网站上获取的信息进行处理
            public void onFinish(String response) {

            }

            @Override
            //对网络连接异常，获取失败进行处理
            public void onError(Exception e) {

            }
        });
    }


}
