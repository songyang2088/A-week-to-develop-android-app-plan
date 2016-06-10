package com.nollec.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nollec on 16-4-7.
 */
public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private SharedPreferences pref2;
    private SharedPreferences pref3;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref1 = getSharedPreferences("data",MODE_PRIVATE);
        pref2 = PreferenceManager.getDefaultSharedPreferences(this);
        pref3 = getPreferences(MODE_PRIVATE);
        editor = pref3.edit();
        editor.putString("姓名", "小明");
        editor.putInt("年龄", 18);
        editor.putLong("性别", 1);
//        只有提交修改保存的数据才会生效。
        editor.commit();
        String name= pref3.getString("姓名", "");
        int age= pref3.getInt("年龄", 0);

        Log.d("LoginActivity",name+age);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        /*accountEdit.setText(pref1.getString("姓名",""));
        passwordEdit.setText(pref1.getInt("年龄",0));*/
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accountEdit.getText().toString();
                String password= passwordEdit.getText().toString();
                if(account.equals("admin")&&password.equals("12345")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
