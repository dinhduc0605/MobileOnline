package com.project.mobileonline.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.ParseObject;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.ProductDetailActivity;
import com.project.mobileonline.utils.StringProcess;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.CART_PRODUCT_ID;
import static com.project.mobileonline.models.Constants.NUMBER_STAR;
import static com.project.mobileonline.models.Constants.PRODUCT_NAME;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_THUMBNAIL_IMAGE;
import static com.project.mobileonline.models.Constants.SHIP_QUANTITY;

/**
 * Created by Nguyen Dinh Duc on 9/21/2015.
 */
public class HistoryOrderAdapter extends ArrayAdapter<ParseObject> {
    public static final String TAG = "HistoryOrderAdapter";
    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;
    Activity context;
    int layoutId;
    ArrayList<ParseObject> items;

    public HistoryOrderAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.layoutId = resource;
        this.items = new ArrayList<>(objects);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder.gridViewItem = (CardView) convertView.findViewById(R.id.historyOrderItemHOI);
            viewHolder.price = (TextView) convertView.findViewById(R.id.productPriceHOI);
            viewHolder.productImage = (ImageView) convertView.findViewById(R.id.productImageHOI);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.productNameHOI);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBarGridItemHOI);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.productQuantityHOI);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ParseObject item = items.get(position);
        final ParseObject product = item.getParseObject(CART_PRODUCT_ID);
        final ViewHolder finalViewHolder = viewHolder;
        Log.w(TAG, position + "");
        finalViewHolder.quantity.setText(context.getString(R.string.quantity) + item.getNumber(SHIP_QUANTITY).toString());
        imageLoader.displayImage(product.getString(PRODUCT_THUMBNAIL_IMAGE), finalViewHolder.productImage, options);
        finalViewHolder.productName.setText(product.getString(PRODUCT_NAME));
        finalViewHolder.ratingBar.setRating(Float.parseFloat(product.getString(NUMBER_STAR)));
        finalViewHolder.price.setText(StringProcess.formatPrice((Integer) product.getNumber(PRODUCT_PRICE)));
        finalViewHolder.gridViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("productName", product.getString(PRODUCT_NAME));
                intent.putExtra("productId", product.getObjectId());
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    class ViewHolder {
        CardView gridViewItem;
        ImageView productImage;
        TextView productName;
        TextView price;
        RatingBar ratingBar;
        TextView quantity;
    }
}
