package com.project.mobileonline.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nguyen Dinh Duc on 9/16/2015.
 */
public class ImageProcess {
    public static Bitmap getBitmapSameSizeToView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(Color.TRANSPARENT);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public static String saveBitmapToFile(String filepath, Bitmap bitmap) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream);
            return filepath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
