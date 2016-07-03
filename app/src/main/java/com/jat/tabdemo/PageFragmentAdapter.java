package com.jat.tabdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by  on 2016/6/15.
 */
public class PageFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager manager;

    public PageFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.manager = fm;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position % fragmentList.size());
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;//没有找到child要求重新加载
    }
}
