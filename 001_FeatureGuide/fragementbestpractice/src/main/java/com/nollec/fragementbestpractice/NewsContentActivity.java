package com.nollec.fragementbestpractice;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by nollec on 16-3-31.
 */
public class NewsContentActivity extends Activity {
//    actionStart方法为了便于其他活动（activity）调用NewsContentActivity，并传递newsTitle，和newsContent参数。
    public static void actionStart(Context context,String newsTitle,String newsContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_content);
        Intent intent=getIntent();
        String newsTitle=intent.getStringExtra("news_title");
        String newsContent=intent.getStringExtra("news_content");
        NewsContentFragment newsContentFragment= (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
}
