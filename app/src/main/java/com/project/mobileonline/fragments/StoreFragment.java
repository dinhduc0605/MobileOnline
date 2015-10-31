package com.project.mobileonline.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.ShowSpecificProduct;
import com.project.mobileonline.adapters.ProductGridViewAdpater;
import com.project.mobileonline.adapters.SlideAdapter;
import com.project.mobileonline.models.Product;
import com.project.mobileonline.utils.ParseHelper;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.project.mobileonline.models.Constants.*;

/**
 * Created by Nguyen Dinh Duc on 8/29/2015.
 */
public class StoreFragment extends Fragment implements View.OnClickListener {
    String[] slideImages = {
            "http://egbw50ulcfs0vaux.zippykid.netdna-cdn.com/wp-content/uploads/2013/10/Google-Nexus-5-Android-Smartphone-at-Google-Play-Store-banner.png",
            "http://martjackstorage.blob.core.windows.net/in-resources/b8dabbe8-70a9-493b-b9ab-6325379c409f/Images/userimages/mobile%20phone%20banner.jpg",
            "http://www.crazecase.com/media/easyslide/Banner2.jpg"
    };
    ViewPager slide;
    CirclePageIndicator indicator;
    GridView recentProductGrid, highProductGrid, mediumProductGrid, lowProductGrid;
    ProductGridViewAdpater recentAdpater, highAdpater, mediumAdpater, lowAdpater;
    ArrayList<Product> recentItems, highItems, mediumItems, lowItems;
    Button recentButton, highButton, mediumButton, lowButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, null);
        initView(view);
        getWidgetControl();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(final View view) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext()).build();
        ImageLoader.getInstance().init(config);
        slide = (ViewPager) view.findViewById(R.id.view_pager);
        SlideAdapter adapter = new SlideAdapter(getContext(), slideImages);
        slide.setAdapter(adapter);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(slide);

        ParseHelper parseHelper = new ParseHelper();
        //recent product

        ParseQuery<ParseObject> recentQuery = parseHelper.getProductQuery(RECENT_PRODUCT);
        recentQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    recentProductGrid = (GridView) view.findViewById(R.id.recentProductGrid);
                    recentAdpater = new ProductGridViewAdpater(getContext(), R.layout.grid_item_layout, list);
                    recentProductGrid.setAdapter(recentAdpater);
                } else {
                    e.printStackTrace();
                }
            }
        });
        recentButton = (Button) view.findViewById(R.id.recentButton);

        //high product
        ParseQuery<ParseObject> highQuery = parseHelper.getProductQuery(HIGH_PRODUCT);
        highQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    highProductGrid = (GridView) view.findViewById(R.id.highProductGrid);
                    highAdpater = new ProductGridViewAdpater(getContext(), R.layout.grid_item_layout, list);
                    highProductGrid.setAdapter(highAdpater);
                } else {
                    e.printStackTrace();
                }
            }
        });
        highButton = (Button) view.findViewById(R.id.highButton);

        //medium product
        ParseQuery<ParseObject> mediumQuery = parseHelper.getProductQuery(MEDIUM_PRODUCT);
        mediumQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    mediumProductGrid = (GridView) view.findViewById(R.id.mediumProductGrid);
                    mediumAdpater = new ProductGridViewAdpater(getContext(), R.layout.grid_item_layout, list);
                    mediumProductGrid.setAdapter(mediumAdpater);
                } else {
                    e.printStackTrace();
                }
            }
        });
        mediumButton = (Button) view.findViewById(R.id.mediumButton);

        //low product
        ParseQuery<ParseObject> lowQuery = parseHelper.getProductQuery(LOW_PRODUCT);
        lowQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    lowProductGrid = (GridView) view.findViewById(R.id.lowProductGrid);
                    lowAdpater = new ProductGridViewAdpater(getContext(), R.layout.grid_item_layout, list);
                    lowProductGrid.setAdapter(lowAdpater);
                } else {
                    e.printStackTrace();
                }
            }
        });
        lowButton = (Button) view.findViewById(R.id.lowButton);

    }

    private void getWidgetControl() {

        recentButton.setOnClickListener(this);
        highButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        lowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String label = "";
        int typeProduct = 0;
        Intent intent = new Intent(getContext(), ShowSpecificProduct.class);
        switch (v.getId()) {
            case R.id.recentButton:
                label = getString(R.string.recentProduct);
                typeProduct = RECENT_PRODUCT;
                break;
            case R.id.highButton:
                label = getString(R.string.highProduct);
                typeProduct = HIGH_PRODUCT;
                break;
            case R.id.mediumButton:
                label = getString(R.string.mediumProduct);
                typeProduct = MEDIUM_PRODUCT;
                break;
            case R.id.lowButton:
                label = getString(R.string.lowProduct);
                typeProduct = LOW_PRODUCT;
                break;
        }
        intent.putExtra("label", label);
        intent.putExtra("typeProduct", typeProduct);
        startActivity(intent);

    }
}
