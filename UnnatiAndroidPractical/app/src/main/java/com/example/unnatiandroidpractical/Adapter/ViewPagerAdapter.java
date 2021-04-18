package com.example.unnatiandroidpractical.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.unnatiandroidpractical.Fragment.DynamicFragment;
import com.example.unnatiandroidpractical.Model.GetCategory;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    private ArrayList<GetCategory.Category> categoriesList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<GetCategory.Category> categoriesList) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.categoriesList = categoriesList;
    }

    @Override
    public Fragment getItem(int position) {

        return DynamicFragment.newInstance(position, categoriesList.get(position).getName());
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
