package com.example.baji.HomeActivity.MatchesFragment.MatchesList.ViewPagerFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.R;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchListViewPagerFragment extends BaseFragment {

    @BindView(R.id.matchListRecyclerView)
    RecyclerView recyclerView;

    MatchListRecyclerViewAdapter adapter;
    List<String> list=new ArrayList<>();

    View Fview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_list_view_pager_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        this.Fview=view;
        initViews();
    }

    private void initViews(){
        addItems();
        adapter=new MatchListRecyclerViewAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new VegaLayoutManager());
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void addItems(){
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan");
        list.add("milan33");
    }

}
