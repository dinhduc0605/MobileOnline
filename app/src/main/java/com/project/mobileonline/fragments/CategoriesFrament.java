package com.project.mobileonline.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.CategoriesTabAdapter;

/**
 * Created by Nguyen Dinh Duc on 10/6/2015.
 */
public class CategoriesFrament extends Fragment {
    ViewPager fragmentPager;
    CategoriesTabAdapter adapter;
    AppCompatActivity activity;
    String[] tabTitles;
    public CategoriesFrament() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tabTitles = activity.getResources().getStringArray(R.array.categoriesTab);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.categoriesTab);
        fragmentPager = (ViewPager) view.findViewById(R.id.fragmentPager);
        adapter = new CategoriesTabAdapter(activity.getSupportFragmentManager(), tabTitles);
        fragmentPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(fragmentPager);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }
}
