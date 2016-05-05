package com.devilwwj.loginandregister.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devilwwj.loginandregister.R;
import com.devilwwj.loginandregister.utils.RegexUtils;

/**
 * Created by nollec on 16-4-1.
 */
public class SetPasswordActivity extends Activity  {
    private CleanEditText passwordEdit;
    private CheckBox showPassword;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpwd);
    }

    private void initViews() {
        passwordEdit = (CleanEditText) findViewById(R.id.et_password);
        passwordEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        passwordEdit.setImeOptions(EditorInfo.IME_ACTION_GO);
        passwordEdit.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        passwordEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_GO) {
                    clickNext();
                }
                return false;
            }
        });
        showPassword= (CheckBox) findViewById(R.id.cb_show_password);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (showPassword.isChecked()) {
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        next= (Button) findViewById(R.id.bt_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetPasswordActivity.this,SetUserNameActivity.class);
                startActivity(intent);
            }
        });


    }


    public boolean checkInput(String password) {
        // 密码为空时提示
        if (password == null || password.trim().equals("")) {
            Toast.makeText(this, R.string.tip_password_can_not_be_empty, Toast.LENGTH_LONG)
                    .show();
        } else {
          return true;
        }

        return false;
    }
    private void clickNext() {

        String password = passwordEdit.getText().toString();
        if (checkInput(password)) {
            // TODO: 进入设置用户名界面
            Intent intent=new Intent(this,SetUserNameActivity.class);
            startActivity(intent);
        }
    }
}
