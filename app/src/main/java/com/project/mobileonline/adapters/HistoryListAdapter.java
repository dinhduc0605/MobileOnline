package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.project.mobileonline.R;
import com.project.mobileonline.activities.HistoryDetailActivity;
import com.project.mobileonline.models.Order;

import java.util.ArrayList;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class HistoryListAdapter extends ArrayAdapter<Order> {
    Context context;
    int layoutId;
    ArrayList<Order> orders;

    public HistoryListAdapter(Context context, int resource, ArrayList<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        orders = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.historyLvItem = (CardView) convertView.findViewById(R.id.histotyLvItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.historyLvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HistoryDetailActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        CardView historyLvItem;
    }
}
