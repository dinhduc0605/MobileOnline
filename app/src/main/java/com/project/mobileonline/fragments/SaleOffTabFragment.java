package com.project.mobileonline.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mobileonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleOffTabFragment extends Fragment {


    public SaleOffTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale_off_tab, container, false);
    }


}
