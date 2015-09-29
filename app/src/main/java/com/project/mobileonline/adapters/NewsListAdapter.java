package com.project.mobileonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.mobileonline.R;
import com.project.mobileonline.models.News;

import java.util.ArrayList;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class NewsListAdapter extends ArrayAdapter<News> {
    Context context;
    int layoutId;
    ArrayList<News> newsList;

    public NewsListAdapter(Context context, int resource, ArrayList<News> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        newsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_news);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_news);
            viewHolder.summary = (TextView) convertView.findViewById(R.id.summary_news);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        News news = newsList.get(position);
//        viewHolder.summary.setText(news.getSummary());
//        viewHolder.title.setText(news.getTitle());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView summary;
    }
}
