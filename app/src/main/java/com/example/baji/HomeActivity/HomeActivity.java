package com.example.baji.HomeActivity;

import android.os.Bundle;

import com.example.baji.BaseClasses.BaseActivity;
import com.example.baji.HomeActivity.BajiFragment.BajiFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesFragment;
import com.example.baji.HomeActivity.ProfileFragment.ProfileFragment;
import com.example.baji.HomeActivity.SettingFragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.example.baji.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        loadFragment(new MatchesFragment());

        //loding fragment according to the nav bottom bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_matches:
                        fragment = new MatchesFragment();
                        break;

                    case R.id.nav_baji:
                        fragment = new BajiFragment();
                        break;

                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.nav_setting:
                        fragment = new SettingFragment();
                        break;
                }

                return loadFragment(fragment);
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

}
