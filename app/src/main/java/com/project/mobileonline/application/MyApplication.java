package com.project.mobileonline.application;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Nguyen Dinh Duc on 10/5/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "OYyLBiBkt53DVu4CXCBbr4UgCpQFMoeUisusPQWa", "i1WGIaqAT2Pvqy0E1SY1HhwHbf15KnSJHGBigdl1");
    }
}
