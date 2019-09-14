package com.example.baji.HomeActivity.SettingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.NotificationActivity.NotificationActivity;
import com.example.baji.R;
import com.example.baji.WebViewActivity.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends BaseFragment {

    @BindView(R.id.notification_icon)
    RelativeLayout ntfIcon;

    @BindView(R.id.about_us)
    TextView aboutUs;

    @BindView(R.id.contact_us)
    TextView contactUs;

    @BindView(R.id.watch_demo)
    TextView watchDemo;

    @BindView(R.id.logout)
    TextView logout;

    @BindView(R.id.terms_and_condition)
    TextView termsAndCondition;

    @BindView(R.id.feedback)
    TextView feedback;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_nav_settings_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews();
    }

    private void initViews(){
        ntfIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        termsAndCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        watchDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
