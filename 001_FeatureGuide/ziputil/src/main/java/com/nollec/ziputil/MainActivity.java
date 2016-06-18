package com.nollec.ziputil;

import android.app.Activity;
import android.os.Environment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    private EditText content;
    private Button zipUtil;
    String filePath = null;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (EditText) findViewById(R.id.content);
        zipUtil = (Button) findViewById(R.id.zipUtil);

        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "hello.zip";
        } else
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + "hello.zip";


        file = new File(filePath);
        if (!file.exists()) {
            File dir = new File(file.getParent());
            dir.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        zipUtil.setOnClickListener(new View.OnClickListener() {

            private String zipContent;

            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {

                        String context = content.getText().toString();
                        ZipUtil zipUtil = new ZipUtil();
                        int contextLength=0;
                        String sumcontext=null;

                        for(int i=1;contextLength<200000;i++){
                            contextLength=context.length()*i;
                            sumcontext=context+sumcontext;
                        }


                        try {
                            zipContent = zipUtil.compress(sumcontext);

                            FileOutputStream outStream = new FileOutputStream(file);
                            outStream.write(zipContent.getBytes());

                            outStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "yan suo wan cheng !", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }.start();



            }
        });

    }
}
