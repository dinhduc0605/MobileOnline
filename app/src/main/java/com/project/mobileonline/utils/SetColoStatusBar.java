package com.project.mobileonline.utils;

import android.app.Activity;

import com.project.mobileonline.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by nguyendinhduc on 10/19/15.
 */
public class SetColoStatusBar {
    public static void setColor(Activity activity) {
        SystemBarTintManager manager = new SystemBarTintManager(activity);
        manager.setStatusBarTintEnabled(true);
        manager.setTintResource(R.color.primary_color);
    }
}
