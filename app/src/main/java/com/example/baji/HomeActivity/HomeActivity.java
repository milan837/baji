package com.example.baji.HomeActivity;

import android.os.Bundle;

import com.example.baji.BaseClasses.BaseActivity;
import com.example.baji.HomeActivity.ActiveBajiListFragment.ActiveBajiListFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesFragment;
import com.example.baji.HomeActivity.ProfileFragment.ProfileFragment;
import com.example.baji.HomeActivity.SettingFragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;

import com.example.baji.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    MatchesFragment fragment1 = new MatchesFragment();
    ActiveBajiListFragment fragment2 = new ActiveBajiListFragment();
    ProfileFragment fragment3 = new ProfileFragment();
    SettingFragment fragment4 = new SettingFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //loadFragment(new MatchesFragment());
        loadAllFragment();

        //loding fragment according to the nav bottom bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_matches:
                        fm.beginTransaction().hide(active).show(fragment1).commit();
                        active = fragment1;
                        return true;

                    case R.id.nav_baji:
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                        return true;

                    case R.id.nav_profile:
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                        return true;

                    case R.id.nav_setting:
                        fm.beginTransaction().hide(active).show(fragment4).commit();
                        active = fragment4;
                        return true;
                }

                return false;
            }
        });

    }

    public boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        }
        return false;
    }

    public void loadAllFragment(){
        fm.beginTransaction().add(R.id.fragment_container, fragment4, "3").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();
    }

}
