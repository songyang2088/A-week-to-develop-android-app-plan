package com.nollec.fragementbestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nollec on 16-3-30.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
//        resource为item布局，context为要加载此布局的主activity，objects为数据
        resourceId=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
//        convertView缓存布局，第一次缓存为空，加载新布局，
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
//            直接加载缓存布局
            view = convertView;
        }
        TextView newsTitletext = (TextView) view.findViewById(R.id.news_title);
        newsTitletext.setText(news.getTitle());
        return view;
    }
}
