package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MatchProfileViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    List<String> pageTitle;

    public MatchProfileViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle.get(position);
    }

    public void addFragment(List<String> pageTitleList,List<Fragment> fragmentList){
        this.fragmentList=fragmentList;
        this.pageTitle=pageTitleList;
    }
}
