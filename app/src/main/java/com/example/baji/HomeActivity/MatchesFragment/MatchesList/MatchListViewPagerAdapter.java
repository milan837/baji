package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MatchListViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public MatchListViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addPage(List<Fragment> fragmentList){
        this.fragmentList=fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
