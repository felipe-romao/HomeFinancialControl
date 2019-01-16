package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerAdpter extends FragmentPagerAdapter{
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Context context;

    public FragmentPagerAdpter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        this.context = context;
    }

    public void add(Fragment fragment, String title){
        this.fragments.add(fragment);
        this.titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles.get(position);
    }
}
