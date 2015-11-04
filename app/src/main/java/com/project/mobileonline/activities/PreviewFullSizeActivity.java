package com.project.mobileonline.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.SlideAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.LARGE_SLIDE_IMAGE;
import static com.project.mobileonline.models.Constants.PRODUCT_TABLE;

public class PreviewFullSizeActivity extends AppCompatActivity {
    ViewPager previewLargeSlide;
    SlideAdapter fullSizeAdapter;
    ArrayList<String> slideImages;
    CirclePageIndicator circlePageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_preview_full_size);
        previewLargeSlide = (ViewPager) findViewById(R.id.previewProductFullSize);
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.previewIndicator);
        ParseQuery.getQuery(PRODUCT_TABLE).getInBackground(getIntent().getStringExtra("productId"), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    List<String> images = parseObject.getList(LARGE_SLIDE_IMAGE);
                    slideImages = new ArrayList<>(images);
                    fullSizeAdapter = new SlideAdapter(getBaseContext(), slideImages);
                    previewLargeSlide.setAdapter(fullSizeAdapter);
                    previewLargeSlide.setCurrentItem(getIntent().getIntExtra("position", 0));
                    circlePageIndicator.setViewPager(previewLargeSlide);
                }
            }
        });


    }
}
