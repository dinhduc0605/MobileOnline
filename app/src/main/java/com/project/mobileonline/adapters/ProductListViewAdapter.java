package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.NUMBER_STAR;
import static com.project.mobileonline.models.Constants.OLD_PRODUCT;
import static com.project.mobileonline.models.Constants.PRODUCT_MANUFACTURE;
import static com.project.mobileonline.models.Constants.PRODUCT_NAME;
import static com.project.mobileonline.models.Constants.PRODUCT_OLD_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_QUANTITY;
import static com.project.mobileonline.models.Constants.PRODUCT_SALE_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_THUMBNAIL_IMAGE;
import static com.project.mobileonline.models.Constants.PRODUCT_TYPE;
import static com.project.mobileonline.models.Constants.SALE_OFF_PRODUCT;

/**
 * Created by nguyendinhduc on 10/25/15.
 */
public class ProductListViewAdapter extends ArrayAdapter<ParseObject> {
    public static final String TAG = "ProductListViewAdapter";
    private final DisplayImageOptions options;
    Context context;
    int layoutId;
    ArrayList<ParseObject> products;
    ImageLoader imageLoader;

    public ProductListViewAdapter(Context context, int resource, List<ParseObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.products = new ArrayList<>(objects);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .build();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.remainQuanity);
            viewHolder.otherPriceLv = (TextView) convertView.findViewById(R.id.otherPriceLvItem);
            viewHolder.otherPriceLv.setPaintFlags(viewHolder.otherPriceLv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ParseObject product = products.get(position);
        final ViewHolder finalViewHolder = viewHolder;
        product.fetchIfNeededInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                imageLoader.displayImage(parseObject.getString(PRODUCT_THUMBNAIL_IMAGE), finalViewHolder.thumbnailLv, options);
                finalViewHolder.productNameLv.setText(parseObject.getString(PRODUCT_NAME));
                finalViewHolder.manufactureLv.setText(parseObject.getString(PRODUCT_MANUFACTURE));
                finalViewHolder.priceLv.setText(parseObject.getNumber(PRODUCT_PRICE).toString());
                finalViewHolder.ratingBarLv.setRating(Float.parseFloat(parseObject.getString(NUMBER_STAR)));
                finalViewHolder.quantity.setText(parseObject.getNumber(PRODUCT_QUANTITY).toString());
                if (parseObject.getInt(PRODUCT_TYPE) == SALE_OFF_PRODUCT) {
                    finalViewHolder.priceLv.setText(parseObject.getNumber(PRODUCT_SALE_PRICE).toString());
                    finalViewHolder.otherPriceLv.setText(parseObject.getNumber(PRODUCT_PRICE).toString());
                } else if (parseObject.getInt(PRODUCT_TYPE) == OLD_PRODUCT) {
                    finalViewHolder.priceLv.setText(parseObject.getNumber(PRODUCT_OLD_PRICE).toString());
                    finalViewHolder.otherPriceLv.setText(parseObject.getNumber(PRODUCT_PRICE).toString());
                }
            }
        });
        viewHolder.productLvItem.setOnClickListener(new View.OnClickListener() {
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

    class ViewHolder {
        CardView productLvItem;
        ImageView thumbnailLv;
        TextView productNameLv;
        TextView manufactureLv;
        TextView priceLv;
        TextView otherPriceLv;
        TextView quantity;
        RatingBar ratingBarLv;
    }
}
