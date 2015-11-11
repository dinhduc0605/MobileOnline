package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.HistoryDetailActivity;
import com.project.mobileonline.utils.DateFormat;
import com.project.mobileonline.utils.ParseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.ORDER_QUANTITY;
import static com.project.mobileonline.models.Constants.ORDER_STATUS;
import static com.project.mobileonline.models.Constants.STATUS_ABORT;
import static com.project.mobileonline.models.Constants.STATUS_DONE;
import static com.project.mobileonline.models.Constants.STATUS_PROGRESS;
import static com.project.mobileonline.models.Constants.TOTAL_AMOUNT;

/**
 * Created by Nguyen Dinh Duc on 9/28/2015.
 */
public class HistoryListAdapter extends ArraySwipeAdapter<ParseObject> {
    Context context;
    int layoutId;
    ArrayList<ParseObject> orders;

    public HistoryListAdapter(Context context, int resource, ArrayList<ParseObject> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutId = resource;
        orders = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.historyLvItem = (CardView) convertView.findViewById(R.id.histotyLvItem);
            viewHolder.orderMoney = (TextView) convertView.findViewById(R.id.orderMoney);
            viewHolder.orderQuantity = (TextView) convertView.findViewById(R.id.orderQuantity);
            viewHolder.statusIcon = (ImageView) convertView.findViewById(R.id.status_icon);
            viewHolder.statusContent = (TextView) convertView.findViewById(R.id.status_content);
            viewHolder.orderDate = (TextView) convertView.findViewById(R.id.orderDate);
            viewHolder.yes = (TextView) convertView.findViewById(R.id.yes);
            viewHolder.no = (TextView) convertView.findViewById(R.id.no);
            viewHolder.historySwipeLayout = (SwipeLayout) convertView.findViewById(R.id.historySwipeLayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ParseObject order = orders.get(position);
        viewHolder.orderMoney.setText(order.getNumber(TOTAL_AMOUNT).toString());
        viewHolder.orderQuantity.setText(context.getString(R.string.quantity) + " " + order.getInt(ORDER_QUANTITY));
        switch (order.getInt(ORDER_STATUS)) {
            case STATUS_DONE:
                viewHolder.statusIcon.setImageResource(R.drawable.done);
                viewHolder.statusContent.setText(R.string.done);
                break;
            case STATUS_PROGRESS:
                viewHolder.statusIcon.setImageResource(R.drawable.progress);
                viewHolder.statusContent.setText(R.string.progress);
                break;
            case STATUS_ABORT:
                viewHolder.statusIcon.setImageResource(R.drawable.abort);
                viewHolder.statusContent.setText(R.string.abort);
                break;
        }
        viewHolder.orderDate.setText(DateFormat.changeDateToString(order.getCreatedAt()));
        viewHolder.historyLvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HistoryDetailActivity.class);
                intent.putExtra("order", order.getObjectId());
                context.startActivity(intent);
            }
        });
        viewHolder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orders.remove(order);
                ParseHelper.getOrderedProductList(order).findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        ParseObject.deleteAllInBackground(list);
                        order.deleteInBackground();

                    }
                });
                notifyDataSetChanged();
            }
        });
        viewHolder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.historySwipeLayout.close();
            }
        });
        return convertView;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.historySwipeLayout;
    }

    class ViewHolder {
        CardView historyLvItem;
        TextView orderMoney;
        TextView orderQuantity;
        ImageView statusIcon;
        TextView statusContent;
        TextView orderDate;
        TextView yes, no;
        SwipeLayout historySwipeLayout;
    }
}
