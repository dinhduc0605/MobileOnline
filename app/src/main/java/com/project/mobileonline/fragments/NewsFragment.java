package com.project.mobileonline.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.NewsListAdapter;
import com.project.mobileonline.models.News;

import java.util.ArrayList;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class NewsFragment extends ListFragment {
    NewsListAdapter adapter;
    ArrayList<News> newsArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsArrayList.add(new News());
        newsArrayList.add(new News());
        newsArrayList.add(new News());
        newsArrayList.add(new News());
        adapter = new NewsListAdapter(getContext(), R.layout.list_item_news_history_layout, newsArrayList);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_news_history, null, false);
    }
}
