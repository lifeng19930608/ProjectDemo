package com.see.you.plan.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 15:26
 * desc    :
 * modify  :
 * version : 1.0
 */

public class PageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = new ArrayList<>();

    public PageAdapter(FragmentManager fm) {
        super(fm);
        list.add(new Fragment1());
        list.add(new Fragment1());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
