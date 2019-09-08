package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Match;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.BajiOnboardListFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.CreateNewBaij.CreateNewBajiBottomFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.OpenBajiListFragment;
import com.example.baji.NotificationActivity.NotificationActivity;
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
    TextView teamOneNameTxt;

    @BindView(R.id.team_two_name)
    TextView teamTwoNameTxt;

    @BindView(R.id.matches_time)
    TextView matchesTimeTxt;

    @BindView(R.id.matches_date)
    TextView matchesDateTxt;

    @BindView(R.id.total_baji)
    TextView totalBajiTxt;

    @BindView(R.id.baji_now_button)
    Button createNewBaji;

    @BindView(R.id.team_one_image)
    ImageView teamOneLogoImg;

    @BindView(R.id.team_two_image)
    ImageView teamTwoLogoImg;

    @BindView(R.id.game_title_name)
            TextView gameTitleTxt;

    @BindView(R.id.total_matches_list)
    TextView totalMatchesTxt;

    @BindView(R.id.back_button)
    RelativeLayout backButton;

    @BindView(R.id.notification_icon)
    RelativeLayout ntfIcon;


    MatchesProfileViewPagerAdapter matchesProfileViewPagerAdapter;
    List<String> pageTitleList=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();

    Match match;
    String gameTitle,totalMatches;
    int matchId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_profile_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        match=(Match)getArguments().getSerializable("matches");
        gameTitle=getArguments().getString("gameTitle");
        totalMatches=getArguments().getString("totalMatches");
        matchId=match.getId();

        setDataToView();
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
        bundle.putInt("matchesId",matchId);
        bundle.putSerializable("match",match);

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
                Bundle bundle1=new Bundle();
                bundle1.putSerializable("matches",match);
                CreateNewBajiBottomFragment createNewBajiBottomFragment=CreateNewBajiBottomFragment.getInstance();
                createNewBajiBottomFragment.setArguments(bundle1);
                createNewBajiBottomFragment.show(getChildFragmentManager(),"createNewBajiFragment");
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        ntfIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setDataToView(){
        gameTitleTxt.setText(gameTitle);
        totalMatchesTxt.setText(totalMatches+" Matches");
        teamOneNameTxt.setText(match.getTeamOne().getName());
        teamTwoNameTxt.setText(match.getTeamTwo().getName());
        matchesDateTxt.setText(match.getTimeStamp());
        totalBajiTxt.setText("Total Baji: 20");
        Glide.with(getActivity()).load(match.getTeamTwo().getImageUrl()).into(teamOneLogoImg);
        Glide.with(getActivity()).load(match.getTeamTwo().getImageUrl()).into(teamTwoLogoImg);
    }

}
