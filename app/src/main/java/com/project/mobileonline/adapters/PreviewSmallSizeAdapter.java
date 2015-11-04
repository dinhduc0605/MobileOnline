package com.project.mobileonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.PreviewFullSizeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyendinhduc on 10/28/15.
 */
public class PreviewSmallSizeAdapter extends RecyclerView.Adapter<PreviewSmallSizeAdapter.ViewHolder> {
    private final DisplayImageOptions options;
    private ImageLoader imageLoader;
    Context context;
    ArrayList<String> imageUrls;
    String productId;

    public PreviewSmallSizeAdapter(Context context, List<String> imageUrls, String productId) {
        this.context = context;
        this.imageUrls = new ArrayList<>(imageUrls);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheOnDisk(true).build();
        this.productId = productId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.preview_small_slide_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        imageLoader.displayImage(imageUrls.get(position), holder.imageView, options);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PreviewFullSizeActivity.class);
                intent.putExtra("productId", productId);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.smallSlideImage);
        }
    }

}
