package com.project.mobileonline.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.project.mobileonline.R;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.StringProcess;

import java.util.ArrayList;

import static com.project.mobileonline.models.Constants.NUMBER_STAR;
import static com.project.mobileonline.models.Constants.PRODUCT_NAME;
import static com.project.mobileonline.models.Constants.PRODUCT_PRICE;
import static com.project.mobileonline.models.Constants.PRODUCT_THUMBNAIL_IMAGE;
import static com.project.mobileonline.models.Constants.SHIP_QUANTITY;

/**
 * Created by nguyendinhduc on 11/5/15.
 */
public class CartAdapter extends ArraySwipeAdapter<ParseObject> {
    private final DisplayImageOptions options;
    ArrayList<ParseObject> carts;
    Context context;
    int layoutId;
    ImageLoader imageLoader;

    public CartAdapter(Context context, int resource, ArrayList<ParseObject> objects) {
        super(context, resource, objects);
        carts = objects;
        this.context = context;
        layoutId = resource;
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .build();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
            viewHolder.cartSwipeLayout = (SwipeLayout) convertView.findViewById(R.id.cartSwipeLayout);
            viewHolder.cartSwipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

            viewHolder.thumbnailLv = (ImageView) convertView.findViewById(R.id.cartThumbnailLvItem);
            viewHolder.productNameLv = (TextView) convertView.findViewById(R.id.cartProductNameLvItem);
            viewHolder.priceLv = (TextView) convertView.findViewById(R.id.cartPriceLvItem);
            viewHolder.quantity = (EditText) convertView.findViewById(R.id.cartShipQuantity);
            viewHolder.ratingBarLv = (RatingBar) convertView.findViewById(R.id.cartRatingLvItem);
            viewHolder.yes = (TextView) convertView.findViewById(R.id.yes);
            viewHolder.no = (TextView) convertView.findViewById(R.id.no);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ViewHolder finalViewHolder = viewHolder;
        final ParseObject cart = carts.get(position);
        viewHolder.quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String s = finalViewHolder.quantity.getText().toString();
                if (s.equals("")) {
                    cart.put(SHIP_QUANTITY, 0);
                } else {
                    cart.put(SHIP_QUANTITY, Integer.parseInt(s));
                }
            }
        });
        ParseObject product = cart.getParseObject(Constants.CART_PRODUCT_ID);
        product.fetchFromLocalDatastoreInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    imageLoader.displayImage(parseObject.getString(PRODUCT_THUMBNAIL_IMAGE), finalViewHolder.thumbnailLv, options);
                    finalViewHolder.productNameLv.setText(parseObject.getString(PRODUCT_NAME));
                    finalViewHolder.priceLv.setText(StringProcess.formatPrice((Integer) parseObject.getNumber(PRODUCT_PRICE)));
                    finalViewHolder.ratingBarLv.setRating(Float.parseFloat(parseObject.getString(NUMBER_STAR)));
                    finalViewHolder.quantity.setText(cart.getNumber(SHIP_QUANTITY).toString());

                } else {
                    e.printStackTrace();
                }

            }
        });

        viewHolder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carts.remove(position);

                try {
                    cart.unpin();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.cartSwipeLayout.close();
            }
        });
        return convertView;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.cartSwipeLayout;
    }

    class ViewHolder {
        SwipeLayout cartSwipeLayout;
        ImageView thumbnailLv;
        TextView productNameLv;
        TextView priceLv;
        EditText quantity;
        RatingBar ratingBarLv;
        TextView yes, no;
    }
}
