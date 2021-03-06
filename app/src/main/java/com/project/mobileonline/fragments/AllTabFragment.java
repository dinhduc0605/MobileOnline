package com.project.mobileonline.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ViewSwitcher;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductGridViewAdapter;
import com.project.mobileonline.models.Product;
import com.project.mobileonline.utils.ParseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.project.mobileonline.models.Constants.ALL_PRODUCT;
import static com.project.mobileonline.models.Constants.NORMAL_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTabFragment extends Fragment {
    GridView allProductGrid;
    ProductGridViewAdapter adpater;
    Activity activity;
    ViewSwitcher viewSwitcher;

    public AllTabFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_all_tab, container, false);
        allProductGrid = (GridView) view.findViewById(R.id.allProductGrid);
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.viewSwitcher);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseQuery<ParseObject> query = ParseHelper.getProductQuery(ALL_PRODUCT, NORMAL_PRODUCT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    adpater = new ProductGridViewAdapter(activity, R.layout.grid_item_layout, new ArrayList<ParseObject>(list));
                    allProductGrid.setAdapter(adpater);
                    viewSwitcher.showNext();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

}
