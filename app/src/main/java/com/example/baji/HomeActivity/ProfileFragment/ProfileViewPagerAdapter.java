package com.example.baji.HomeActivity.ProfileFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ProfileViewPagerAdapter extends FragmentPagerAdapter {
    List<String> pageTitle;
    List<Fragment> fragmentList;

    public ProfileViewPagerAdapter(@NonNull FragmentManager fm) {
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

    public void addPage(List<String> pageTitle,List<Fragment> fragmentList){
        this.pageTitle=pageTitle;
        this.fragmentList=fragmentList;
    }
}
