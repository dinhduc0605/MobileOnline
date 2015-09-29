package com.project.mobileonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {

    }
}
