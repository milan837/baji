package com.example.baji.HomeActivity.ProfileFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.OnboardingBajiListFragment;
import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.OpenBajiListFragment;
import com.example.baji.R;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends BaseFragment {

    @BindView(R.id.username)
    TextView usernameTxt;

    @BindView(R.id.amount)
    TextView amountTxt;

    @BindView(R.id.with_draw_button)
    Button withDrawBtn;

    @BindView(R.id.total_baji)
    TextView totalBajiTxt;

    @BindView(R.id.total_win_baji)
    TextView totalWinBajiTxt;

    @BindView(R.id.total_lose_baji)
    TextView totalLoseBajiTxt;


    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ProfileViewPagerAdapter adapter;
    List<String> pageTitle=new ArrayList<>();
    List<Fragment> fragmentList=new ArrayList<>();

    String username,amount,userId="132",profilePic,totalBaji,totalWinBaji,totalLoseBaji;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_nav_profile_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

//        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        userId=sharedPreferences.getString("userId",null);
//        amount=sharedPreferences.getString("amount",null);
//        username=savedInstanceState.getString("username",null);
//        profilePic=sharedPreferences.getString("imageUrl",null);
//        totalBaji=sharedPreferences.getString("totalBaji",null);
//        totalLoseBaji=sharedPreferences.getString("totalLoseBaji",null);
//        totalWinBaji=sharedPreferences.getString("totalWinBaji",null);

        initViews();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        adapter=new ProfileViewPagerAdapter(getChildFragmentManager());
    }

    public void initViews(){

        pageTitle.add("Onboard baji");
        pageTitle.add("Pending Baji");

        Bundle bundle=new Bundle();
        bundle.putString("userId",userId);

        OnboardingBajiListFragment onboardingBajiListFragment=new OnboardingBajiListFragment();
        onboardingBajiListFragment.setArguments(bundle);

        OpenBajiListFragment openBajiListFragment=new OpenBajiListFragment();
        openBajiListFragment.setArguments(bundle);


        fragmentList.add(onboardingBajiListFragment);
        fragmentList.add(openBajiListFragment);

        adapter.addPage(pageTitle,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();

    }


}
