package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.BajiOnBoardListFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.OpenBajiListFragment;
import com.example.baji.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchProfileFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.team_one_name)
    TextView teamOneName;

    @BindView(R.id.team_two_name)
    TextView teamTwoName;

    @BindView(R.id.matches_time)
    TextView matchesTime;

    @BindView(R.id.matches_date)
    TextView matchesDate;

    @BindView(R.id.total_baji)
    TextView totalBaji;

    @BindView(R.id.baji_now_button)
    Button createNewBaji;

    @BindView(R.id.team_one_image)
    ImageView teamOneLogo;

    @BindView(R.id.team_two_image)
    ImageView teamTwoLogo;
//
//    @BindView(R.id.nestedScrollView)
//    NestedScrollView nestedScrollView;

    MatchProfileViewPagerAdapter matchProfileViewPagerAdapter;
    List<String> pageTitleList=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_profile_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews();
    }

    private void initViews(){
        //nestedScrollView.setFillViewport(true);
        pageTitleList.add("Open Baji");
        pageTitleList.add("Baji Onboard");

        fragmentList.add(new OpenBajiListFragment());
        fragmentList.add(new BajiOnBoardListFragment());

        matchProfileViewPagerAdapter=new MatchProfileViewPagerAdapter(getActivity().getSupportFragmentManager());
        matchProfileViewPagerAdapter.addFragment(pageTitleList,fragmentList);
        viewPager.setAdapter(matchProfileViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        matchProfileViewPagerAdapter.notifyDataSetChanged();
    }
}
