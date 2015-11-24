package com.project.mobileonline.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.ViewSwitcher;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.activities.ShowSpecificProduct;
import com.project.mobileonline.adapters.ProductGridViewAdapter;
import com.project.mobileonline.adapters.SlideAdapter;
import com.project.mobileonline.utils.ParseHelper;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.BANNER_URL;
import static com.project.mobileonline.models.Constants.HIGH_PRODUCT;
import static com.project.mobileonline.models.Constants.LOW_PRODUCT;
import static com.project.mobileonline.models.Constants.MEDIUM_PRODUCT;
import static com.project.mobileonline.models.Constants.NORMAL_PRODUCT;
import static com.project.mobileonline.models.Constants.RECENT_PRODUCT;

/**
 * Created by Nguyen Dinh Duc on 8/29/2015.
 */
public class StoreFragment extends Fragment implements View.OnClickListener {
    Context context;
    ArrayList<String> slideImages = new ArrayList<>();
    ViewPager slide;
    CirclePageIndicator indicator;
    GridView recentProductGrid, highProductGrid, mediumProductGrid, lowProductGrid;
    ProductGridViewAdapter recentAdpater, highAdpater, mediumAdpater, lowAdpater;
    Button recentButton, highButton, mediumButton, lowButton;
    ArrayList<ParseObject> listBanner = new ArrayList<>(),
            listRecentProduct = new ArrayList<>(),
            listHighProduct = new ArrayList<>(),
            listMediumProduct = new ArrayList<>(),
            listLowProduct = new ArrayList<>();
    List<ParseObject> listCompoundProduct = new ArrayList<>();

    ProgressBar storeProgress;
    ViewSwitcher viewSwitcher;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

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
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher);
        storeProgress = (ProgressBar) view.findViewById(R.id.storeProgress);
        slide = (ViewPager) view.findViewById(R.id.view_pager);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        recentProductGrid = (GridView) view.findViewById(R.id.recentProductGrid);
        highProductGrid = (GridView) view.findViewById(R.id.highProductGrid);
        mediumProductGrid = (GridView) view.findViewById(R.id.mediumProductGrid);
        lowProductGrid = (GridView) view.findViewById(R.id.lowProductGrid);

        recentButton = (Button) view.findViewById(R.id.recentButton);
        highButton = (Button) view.findViewById(R.id.highButton);
        mediumButton = (Button) view.findViewById(R.id.mediumButton);
        lowButton = (Button) view.findViewById(R.id.lowButton);

        new LoadDataTask().execute();
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
        Intent intent = new Intent(context, ShowSpecificProduct.class);
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

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ParseQuery<ParseObject> query = ParseHelper.getBannerImage();
            try {
                listBanner = new ArrayList<>(query.find());
                for (int i = 0; i < listBanner.size(); i++) {
                    slideImages.add(listBanner.get(i).getString(BANNER_URL));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            //recent product

            ParseQuery<ParseObject> recentQuery = ParseHelper.getProductQuery(RECENT_PRODUCT, NORMAL_PRODUCT);
            try {
                listRecentProduct = new ArrayList<>(recentQuery.find());
            } catch (ParseException e) {
                e.printStackTrace();
            }


            //high product
            ParseQuery<ParseObject> highQuery = ParseHelper.getProductQuery(HIGH_PRODUCT, NORMAL_PRODUCT);
            try {
                listHighProduct = new ArrayList<>(highQuery.find());
            } catch (ParseException e) {
                e.printStackTrace();
            }


            //medium product
            ParseQuery<ParseObject> mediumQuery = ParseHelper.getProductQuery(MEDIUM_PRODUCT, NORMAL_PRODUCT);
            try {
                listMediumProduct = new ArrayList<>(mediumQuery.find());
            } catch (ParseException e) {
                e.printStackTrace();
            }


            //low product
            ParseQuery<ParseObject> lowQuery = ParseHelper.getProductQuery(LOW_PRODUCT, NORMAL_PRODUCT);
            try {
                listLowProduct = new ArrayList<>(lowQuery.find());
            } catch (ParseException e) {
                e.printStackTrace();
            }

//            List<ParseQuery<ParseObject>> queries = new ArrayList<>();
//            queries.add(recentQuery);
//            queries.add(highQuery);
//            queries.add(mediumQuery);
//            queries.add(lowQuery);
//            ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
//            try {
//                listCompoundProduct = mainQuery.find();
//                for (int i = 0; i < 3; i++) {
//                    listRecentProduct.add(listCompoundProduct.get(i));
//                    listHighProduct.add(listCompoundProduct.get(i + 3));
//                    listMediumProduct.add(listCompoundProduct.get(i + 6));
//                    listLowProduct.add(listCompoundProduct.get(i + 9));
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewSwitcher.showNext();
            SlideAdapter adapter = new SlideAdapter(context, slideImages);
            slide.setAdapter(adapter);
            indicator.setViewPager(slide);

            recentAdpater = new ProductGridViewAdapter(context, R.layout.grid_item_layout, listRecentProduct);
            recentProductGrid.setAdapter(recentAdpater);

            highAdpater = new ProductGridViewAdapter(context, R.layout.grid_item_layout, listHighProduct);
            highProductGrid.setAdapter(highAdpater);

            mediumAdpater = new ProductGridViewAdapter(context, R.layout.grid_item_layout, listMediumProduct);
            mediumProductGrid.setAdapter(mediumAdpater);

            lowAdpater = new ProductGridViewAdapter(context, R.layout.grid_item_layout, listLowProduct);
            lowProductGrid.setAdapter(lowAdpater);

        }
    }
}
