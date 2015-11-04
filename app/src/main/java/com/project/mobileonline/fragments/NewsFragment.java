package com.project.mobileonline.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.NewsContentActivity;
import com.project.mobileonline.adapters.NewsListAdapter;
import com.project.mobileonline.models.News;
import com.project.mobileonline.utils.ParseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class NewsFragment extends ListFragment {
    NewsListAdapter adapter;
    String content;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news_history, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseHelper parseHelper = new ParseHelper();
        ParseQuery<ParseObject> query = parseHelper.getNewsQuery();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                adapter = new NewsListAdapter(context, R.layout.list_item_news_layout, list);
                setListAdapter(adapter);
            }
        });
    }
}
