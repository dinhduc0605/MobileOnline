package com.project.mobileonline.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.mobileonline.fragments.BestSellerTabFragment;
import com.project.mobileonline.fragments.ManufactureTabFragment;
import com.project.mobileonline.fragments.OldProductTabFragment;
import com.project.mobileonline.fragments.SaleOffTabFragment;

/**
 * Created by Nguyen Dinh Duc on 10/7/2015.
 */
public class CategoriesTabAdapter extends FragmentPagerAdapter {
    String[] tabTitles;

    public CategoriesTabAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = new ManufactureTabFragment();
                break;
            case 1:
                fragment = new BestSellerTabFragment();
                break;
            case 2:
                fragment = new SaleOffTabFragment();
                break;
            case 3:
                fragment = new OldProductTabFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
