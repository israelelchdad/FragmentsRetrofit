package com.example.frame2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class simpelPageAdapter extends FragmentStatePagerAdapter {

    List<Fragment> myfrgments;

    public simpelPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        myfrgments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return myfrgments.get(position);
    }

    @Override
    public int getCount() {
        return myfrgments.size();
    }
}
