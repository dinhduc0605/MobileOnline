package com.project.mobileonline.custom;

import android.content.Context;
import android.widget.Scroller;

/**
 * Created by nguyendinhduc on 11/25/15.
 */
public class ViewpagerScroller extends Scroller{
    private int mScrollDuration = 1000;
    public ViewpagerScroller(Context context) {
        super(context);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }
}
