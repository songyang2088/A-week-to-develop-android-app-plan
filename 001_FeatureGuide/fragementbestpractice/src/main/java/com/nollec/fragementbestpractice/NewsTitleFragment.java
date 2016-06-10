package com.nollec.fragementbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nollec on 16-3-31.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<News> newsList;
    private NewsAdapter adapter;
    private ListView newsListView;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList=getNews();//初始化新闻数据
        Log.d("newsList",newsList.toString());
        adapter=new NewsAdapter(activity,R.layout.news_item,newsList);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.news_title_frag,container,false);
        newsListView= (ListView) view.findViewById(R.id.news_title_list_view);
        newsListView.setAdapter(adapter);
        newsListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news=newsList.get(position);
        if(isTwoPane){
            NewsContentFragment newsContentFragment= (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(),news.getContent());
        }else{
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());

        }

    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a roommate, adjust to a new social scene and survive less-than-stellar dining hall food.Students with learning disabilities will face these transitions while also grappling with a few more hurdles. ");
        News news2=new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive involved in the tech giant's Android phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker.");
        newsList.add(news1);
        newsList.add(news2);
        return newsList;

    }
}
