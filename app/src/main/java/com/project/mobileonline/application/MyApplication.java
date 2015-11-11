package com.project.mobileonline.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;

/**
 * Created by Nguyen Dinh Duc on 10/5/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "G6gvYz4BlEp62zH8VJwHKhzzTZT4gTIUvrYvp4mp", "zqpD8hnBXNJMtBSyAAktltqaS37q8VBwzx29DoEY");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(15)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
