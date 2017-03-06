package com.zrx.moonagain.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 频道
 * Created by Schnee on 2017/2/10.
 */

public class ChannelAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> viewsList;
    private ArrayList<String> tabNamesList;

    public ChannelAdapter(FragmentManager fm,ArrayList<String> names, ArrayList<Fragment> views) {
        super(fm);
        viewsList = views;
        tabNamesList = names;
    }

    @Override
    public int getCount() {
        return viewsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNamesList.get(position);
    }


    @Override
    public Fragment getItem(int position) {
        return viewsList.get(position);
    }
}
