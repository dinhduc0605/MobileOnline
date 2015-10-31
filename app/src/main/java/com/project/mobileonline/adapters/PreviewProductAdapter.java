package com.project.mobileonline.adapters;

import android.app.ActionBar;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.mobileonline.R;
import com.project.mobileonline.models.Product;

import java.util.ArrayList;

/**
 * Created by nguyendinhduc on 10/28/15.
 */
public class PreviewProductAdapter extends RecyclerView.Adapter<PreviewProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> products;

    public PreviewProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(10, 0, 10, 0);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.l));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
