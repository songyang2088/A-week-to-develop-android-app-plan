package com.devilwwj.loginandregister.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devilwwj.loginandregister.R;

import java.util.regex.Pattern;

/**
 * Created by nollec on 16-4-1.
 */
public class SetUserNameActivity extends Activity {

    private CleanEditText userNameEdit;
    private Button doneButton;
    private String userName;
    private ImageView backImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_name);
        userNameEdit = (CleanEditText) findViewById(R.id.et_username);
//        设置输入完成后软键盘显示完成。
        userNameEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        userNameEdit.setImeOptions(EditorInfo.IME_ACTION_GO);
        userNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_GO) {
                    clickLogin();
                }
                return false;
            }
        });;
        doneButton = (Button) findViewById(R.id.bt_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        backImageView = (ImageView) findViewById(R.id.iv_back);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void clickLogin(){
        userName =userNameEdit.getText().toString();
        if(checkInput(userName)){
//            请求登录服务器

        }
    }
    public boolean checkInput(String userName){
        if(!checkUserName(userName)){
            Toast.makeText(this,"用户名格式错误,请重新输入",Toast.LENGTH_LONG).show();
        }else{
//            判断密码是否被占用，
//            if(checkUserName(userName)) {
//
//            }else{
                Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
                return true;
//            }
        }


        return false;
    }
    public  boolean checkUserName(String userName){
//        只能输入6-16个以字母开头、可带数字、“_”的字串
        String regex="^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,15}$";
        return Pattern.matches(regex,userName);
    }
}
