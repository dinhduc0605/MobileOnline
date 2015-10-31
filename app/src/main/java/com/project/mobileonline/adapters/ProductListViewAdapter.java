package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.mobileonline.R;
import com.project.mobileonline.activities.ProductDetailActivity;
import com.project.mobileonline.models.Product;

import java.util.ArrayList;

/**
 * Created by nguyendinhduc on 10/25/15.
 */
public class ProductListViewAdapter extends ArrayAdapter<Product> {
    Context context;
    int layoutId;
    ArrayList<Product> items;

    public ProductListViewAdapter(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder.thumbnailLv = (ImageView) convertView.findViewById(R.id
                    .productThumbnailLvItem);
            viewHolder.productLvItem = (CardView) convertView.findViewById(R.id.productLvItem);
            viewHolder.productNameLv = (TextView) convertView.findViewById(R.id.productNameLvItem);
            viewHolder.manufactureLv = (TextView) convertView.findViewById(R.id.manufactureLvItem);
            viewHolder.priceLv = (TextView) convertView.findViewById(R.id.priceLvItem);
            viewHolder.ratingBarLv = (RatingBar) convertView.findViewById(R.id.ratingLvItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.productLvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        CardView productLvItem;
        ImageView thumbnailLv;
        TextView productNameLv;
        TextView manufactureLv;
        TextView priceLv;
        RatingBar ratingBarLv;
    }
}
