package com.nollec.webviewtest;

/**
 * Created by nollec on 16-6-30.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
