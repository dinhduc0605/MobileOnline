package com.project.mobileonline.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.ProductListViewAdapter;
import com.project.mobileonline.models.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OldProductTabFragment extends ListFragment {
    ProductListViewAdapter adapter;
    ArrayList<Product> products;

    public OldProductTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_old_product_tab, container, false);
        products = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            products.add(new Product());
        }
        adapter = new ProductListViewAdapter(getContext(), R.layout.list_item_product_categories, products);
        setListAdapter(adapter);
        return view;
    }


}
