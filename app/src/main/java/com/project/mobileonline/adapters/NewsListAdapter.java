package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseObject;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.NewsContentActivity;
import com.project.mobileonline.utils.HelperClass;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.CONTENT;
import static com.project.mobileonline.models.Constants.NEWS_IMAGE;
import static com.project.mobileonline.models.Constants.TITLE;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class NewsListAdapter extends ArrayAdapter<ParseObject> {
    private static final String TAG = "NewsListAdaper";
    private final DisplayImageOptions options;
    Context context;
    int layoutId;
    ArrayList<ParseObject> newsList;
    ImageLoader imageLoader;

    public NewsListAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        newsList = new ArrayList<>(objects);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheOnDisk(true).build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.w(TAG, position + "");
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.newsItem = (CardView) convertView.findViewById(R.id.news_item);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_news);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_news);
            viewHolder.summary = (TextView) convertView.findViewById(R.id.summary_news);
            viewHolder.datePublish = (TextView) convertView.findViewById(R.id.newsPublishDate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ParseObject news = newsList.get(position);
//        viewHolder.summary.setText(news.getString());
        viewHolder.title.setText(news.getString(TITLE));
//        imageLoader.displayImage(news.getString(NEWS_IMAGE), viewHolder.imageView, options);
        String url = news.getString(NEWS_IMAGE);
        url = url.substring(url.indexOf(",") + 1);
        url = url.substring(0, url.indexOf('"'));
        Log.w(TAG, url);
        byte[] bytes = Base64.decode(url, Base64.DEFAULT);
        Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        viewHolder.imageView.setImageBitmap(image);
        viewHolder.datePublish.setText(HelperClass.changeDateFormat(news.getCreatedAt()));
        viewHolder.newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsContentActivity.class);
                intent.putExtra("content", news.getString(CONTENT));
                intent.putExtra("title", news.getString(TITLE));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        CardView newsItem;
        ImageView imageView;
        TextView title;
        TextView summary;
        TextView datePublish;
    }
}
