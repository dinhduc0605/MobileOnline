package com.project.mobileonline.application;

import android.app.Application;

import com.parse.Parse;
import com.project.mobileonline.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Dinh Duc on 10/5/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "G6gvYz4BlEp62zH8VJwHKhzzTZT4gTIUvrYvp4mp", "zqpD8hnBXNJMtBSyAAktltqaS37q8VBwzx29DoEY");
    }
}
