package com.example.baji.NotificationActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseActivity;
import com.example.baji.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.back_button)
    RelativeLayout backIcon;

    NotificationRecyclerViewAdapter adapter;
    List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        initView();

    }

    public void initView(){
        list.add("Milan Shrestha");
        list.add("Kumar Shrestha");
        list.add("Anil Shrestha");
        list.add("susant Shrestha");
        list.add("Priya Shrestha");
        list.add("anjana Shrestha");
        list.add("seenu Shrestha");
        list.add("rajat Shrestha");


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter=new NotificationRecyclerViewAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.setNestedScrollingEnabled(false);

    }



}
