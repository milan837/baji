package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.ViewPagerFragment.MatchListViewPagerFragment;
import com.example.baji.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchListFragment extends BaseFragment {

    @BindView(R.id.match_list_viewpager)
    ViewPager viewPager;

    View fview;
    MatchListViewPagerAdapter viewPagerAdapter;
    List<Fragment> fragmentList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_list_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        this.fview=view;
        initViews();
    }

    private void initViews(){
        viewPagerAdapter=new MatchListViewPagerAdapter(getActivity().getSupportFragmentManager());

        fragmentList.add(new MatchListViewPagerFragment());
        fragmentList.add(new MatchListViewPagerFragment());
        fragmentList.add(new MatchListViewPagerFragment());
        fragmentList.add(new MatchListViewPagerFragment());
        fragmentList.add(new MatchListViewPagerFragment());

        viewPagerAdapter.addPage(fragmentList);

        viewPager.setAdapter(viewPagerAdapter);


    }


}
