package com.project.mobileonline.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductGridViewAdpater;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.models.Product;
import com.project.mobileonline.utils.ParseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTabFragment extends Fragment {
    GridView allProductGrid;
    ProductGridViewAdpater adpater;
    ArrayList<Product> items;

    public AllTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_manufacture_tab, container, false);
        allProductGrid = (GridView) view.findViewById(R.id.allProductGrid);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ParseHelper parseHelper = new ParseHelper();
        ParseQuery<ParseObject> query = parseHelper.getProductQuery(Constants.ALL_PRODUCT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    adpater = new ProductGridViewAdpater(getContext(), R.layout.grid_item_layout, list);

                    allProductGrid.setAdapter(adpater);
                }
            }
        });
    }

}
