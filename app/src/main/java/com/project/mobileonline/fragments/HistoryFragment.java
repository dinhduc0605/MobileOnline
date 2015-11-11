package com.project.mobileonline.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.HistoryListAdapter;
import com.project.mobileonline.utils.ParseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class HistoryFragment extends ListFragment {
    HistoryListAdapter adapter;
    Context context;
    ViewSwitcher viewSwitcher;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_history, null, false);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseQuery<ParseObject> query = ParseHelper.getHistoryOrderQuery();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    adapter = new HistoryListAdapter(context, R.layout.list_item_history_layout, new ArrayList<>(list));
                    setListAdapter(adapter);
                    viewSwitcher.showNext();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}
