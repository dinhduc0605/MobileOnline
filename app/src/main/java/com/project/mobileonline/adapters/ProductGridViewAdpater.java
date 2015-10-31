package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseObject;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.ProductDetailActivity;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.models.Product;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.PREVIEW_IMAGE;
import static com.project.mobileonline.models.Constants.PRODUCT_NAME;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.THUMBNAIL_IMAGE;

/**
 * Created by Nguyen Dinh Duc on 9/21/2015.
 */
public class ProductGridViewAdpater extends ArrayAdapter<ParseObject> {
    Context context;
    int layoutId;
    ArrayList<ParseObject> items;

    public ProductGridViewAdpater(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.items = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder.gridViewItem = (CardView) convertView.findViewById(R.id.gridViewItem);
            viewHolder.price = (TextView) convertView.findViewById(R.id.productPrice);
            viewHolder.productImage = (ImageView) convertView.findViewById(R.id.productImage);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.productName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ParseObject product = items.get(position);
        viewHolder.gridViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);
            }
        });
//        imageLoader.displayImage(product.getString(THUMBNAIL_IMAGE), viewHolder.productImage);
        viewHolder.productName.setText(product.getString(PRODUCT_NAME));
        viewHolder.price.setText(product.getNumber(PRODUCT_PRICE).toString());
        return convertView;
    }

    class ViewHolder {
        CardView gridViewItem;
        ImageView productImage;
        TextView productName;
        TextView price;
    }
}
