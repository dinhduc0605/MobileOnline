package com.project.mobileonline.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by nguyendinhduc on 10/19/15.
 */
public class SlideAdapter extends PagerAdapter {
    Context context;
    String[] slideImages;

    public SlideAdapter(Context context, String[] slideImages) {
        this.context = context;
        this.slideImages = slideImages;
    }

    @Override
    public int getCount() {
        return slideImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView slideImage = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        slideImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        slideImage.setLayoutParams(layoutParams);
        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(slideImages[position], slideImage);
        container.addView(slideImage);
        return slideImage;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
