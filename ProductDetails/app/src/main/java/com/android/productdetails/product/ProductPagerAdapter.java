package com.android.productdetails.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ProductPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<ProductFragment> productList;

    public ProductPagerAdapter(FragmentManager fm, ArrayList<ProductFragment> tutorialsList) {
        super(fm);
        this.productList = tutorialsList;
    }

    @Override
    public Fragment getItem(int position) {
        return productList.get(position);
    }

    @Override
    public int getCount() {
        return productList.size();
    }
}
