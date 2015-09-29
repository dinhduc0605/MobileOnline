package com.project.mobileonline.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.HistoryListAdapter;
import com.project.mobileonline.models.Order;

import java.util.ArrayList;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class HistoryFragment extends ListFragment {
    ArrayList<Order> orders = new ArrayList<>();
    HistoryListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        adapter = new HistoryListAdapter(getContext(), R.layout.list_item_news_history_layout, orders);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_news_history, null, false);
    }

}
