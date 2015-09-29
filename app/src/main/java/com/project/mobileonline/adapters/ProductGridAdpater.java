package com.project.mobileonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.mobileonline.R;
import com.project.mobileonline.models.ProductGridItem;

import java.util.ArrayList;

/**
 * Created by Nguyen Dinh Duc on 9/21/2015.
 */
public class ProductGridAdpater extends ArrayAdapter<ProductGridItem> {
    Context context;
    int layoutId;
    ArrayList<ProductGridItem> items;

    public ProductGridAdpater(Context context, int resource, ArrayList<ProductGridItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder.price = (TextView) convertView.findViewById(R.id.productPrice);
            viewHolder.productImage = (ImageView) convertView.findViewById(R.id.productImage);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.productName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProductGridItem item = items.get(position);
        viewHolder.productImage.setImageResource(R.drawable.l);
        viewHolder.productName.setText("Iphone 6s");
        viewHolder.price.setText("16.000.000đ");
        return convertView;
    }

    class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView price;
    }
}
