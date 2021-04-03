package com.simanjit.dxminds.adapter;

import com.simanjit.dxminds.fragment.DynamicFragment;
import com.simanjit.dxminds.model.Articles;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PlansPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<List<Articles>> tabTitle;

    public PlansPagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<List<Articles>> tabTitle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return DynamicFragment.newInstance(tabTitle.get(position), position);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}