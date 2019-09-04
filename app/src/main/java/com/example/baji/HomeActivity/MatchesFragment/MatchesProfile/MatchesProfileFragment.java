package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.BajiOnboardListFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.CreateNewBaij.CreateNewBajiBottomFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.OpenBajiListFragment;
import com.example.baji.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;

public class MatchesProfileFragment extends BaseFragment {

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


    MatchesProfileViewPagerAdapter matchesProfileViewPagerAdapter;
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        matchesProfileViewPagerAdapter = new MatchesProfileViewPagerAdapter(getChildFragmentManager());
    }

    private void initViews(){

        pageTitleList.add("Open Baji");
        pageTitleList.add("Baji Onboard");

        Bundle bundle=new Bundle();
        bundle.putInt("matchesId",2);

        OpenBajiListFragment openBajiListFragment=new OpenBajiListFragment();
        BajiOnboardListFragment bajiOnBoardListFragment=new BajiOnboardListFragment();

        openBajiListFragment.setArguments(bundle);
        bajiOnBoardListFragment.setArguments(bundle);

        fragmentList.add(openBajiListFragment);
        fragmentList.add(bajiOnBoardListFragment);

        matchesProfileViewPagerAdapter.addFragment(pageTitleList,fragmentList);
        viewPager.setAdapter(matchesProfileViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        matchesProfileViewPagerAdapter.notifyDataSetChanged();

        createNewBaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewBajiBottomFragment.getInstance().show(getChildFragmentManager(),"createNewBajiFragment");
            }
        });

    }

}
