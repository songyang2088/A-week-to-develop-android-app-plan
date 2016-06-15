package com.nollec.download;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    /* SD卡根目录 */
    private File rootDie;
    /* 输出文件名称 */
    public static String filename="";
    /* 进度条对话框 */
    private ProgressDialog pDialog;
    private File newFile;
    boolean isok = false;
    public static HttpURLConnection conn;
    //下载APP网址（url）
    public static String url="http://wiki.nollec.com/wiki/images/ZH8000_UIG.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndCreateDir();
        Log.d("checkAndCreateDir", newFile + "");


    }

    //    private boolean ExistSDCard() {
//        if (android.os.Environment.getExternalStorageState().equals(
//                android.os.Environment.MEDIA_MOUNTED)) {
//            return true;
//        } else
//            return false;
//    }
    /* 检查sdcard并创建目录文件 */
    private void checkAndCreateDir() {
/* 获取sdcard目录 */
        rootDie = Environment.getExternalStorageDirectory();
/* 新文件的目录 */
        newFile = new File(rootDie + "/download1/");
        if (!newFile.exists()) {
/* 如果文件不存在就创建目录 */
            newFile.mkdirs();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
/* 实例化进度条对话框 */
        pDialog = new ProgressDialog(this);
/* 进度条对话框属性设置 */
        pDialog.setMessage("正在下载中...");
/* 进度值最大100 */
        pDialog.setMax(100);
/* 水平风格进度条 */
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
/* 无限循环模式 */
        pDialog.setIndeterminate(false);
/* 可取消 */
        pDialog.setCancelable(false);
/* 显示对话框 */
        pDialog.show();
        return pDialog;
    }

    class MyLoadAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL myurl = new URL(params[0]);
                conn = (HttpURLConnection) myurl.openConnection();
/* URL属性设置 */
                conn.setRequestMethod("GET");
/* URL建立连接 */
                conn.connect();
/* 下载文件的大小 */
                int fileOfLength = conn.getContentLength();

/* 每次下载的大小与总下载的大小 */
                int totallength = 0;
                int length = 0;

//获取文件名
//                String urlName= conn.getHeaderField("Content-Disposition");//通过Content-Disposition获取文件名
//                Log.d("urlName", urlName);

                String filename=url.substring(url.lastIndexOf("/") + 1);



                /* 输入流 */
                InputStream in = conn.getInputStream();
                /* 输出流 */

                FileOutputStream out = new FileOutputStream(new File(newFile, filename));
/* 缓存模式，下载文件 */
                byte[] buff = new byte[1024 * 1024];
                while ((length = in.read(buff)) > 0) {
                    totallength += length;
                    String str1 = "" + (int) ((totallength * 100) / fileOfLength);
                    publishProgress(str1);
                    out.write(buff, 0, length);
                }
/* 关闭输入输出流 */
                in.close();
                out.flush();
                out.close();


            } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override

/* 预处理UI线程 */
        protected void onPreExecute() {
            showDialog(0);
            super.onPreExecute();
        }

        @Override
        /* 结束时的UI线程 */
        protected void onPostExecute(String s) {
            dismissDialog(0);
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_LONG).show();
        }

        @Override
        /* 处理UI线程，会被多次调用,触发事件为publicProgress方法 */
        protected void onProgressUpdate(String... values) {
            /* 进度显示 */
            pDialog.setProgress(Integer.parseInt(values[0]));
        }
    }

    public void download(View v) {
        new MyLoadAsyncTask().execute(url);
    }

}
