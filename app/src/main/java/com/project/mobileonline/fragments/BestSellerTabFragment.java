package com.project.mobileonline.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductListViewAdapter;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.utils.ParseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BestSellerTabFragment extends ListFragment {
    ProductListViewAdapter adapter;
    Activity activity;
    ViewSwitcher viewSwitcher;
    public BestSellerTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_best_seller_tab, container, false);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher);
        ParseQuery<ParseObject> query = ParseHelper.getBestsellerQuery();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> bestsellers, ParseException e) {
                if (e == null) {
                    List<ParseObject> products = new ArrayList<>();
                    for (ParseObject parseObject : bestsellers) {
                        products.add(parseObject.getParseObject(Constants.BESTSELLER_PRODUCT_ID));
                    }
                    adapter = new ProductListViewAdapter(activity, R.layout.list_item_product_categories, products);
                    setListAdapter(adapter);
                    viewSwitcher.showNext();
                } else {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
