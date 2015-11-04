package com.project.mobileonline.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Nguyen Dinh Duc on 9/16/2015.
 */
public class HelperClass {
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

    public static String changeDateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        return format.format(date);
    }
}
