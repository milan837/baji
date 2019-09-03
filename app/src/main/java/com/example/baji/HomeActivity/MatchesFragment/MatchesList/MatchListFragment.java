package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baji.BaseClasses.BaseFragment;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWIthMatchResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Matches;
import com.example.baji.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchListFragment extends BaseFragment {

    @BindView(R.id.game_with_match_recycler_view)
            RecyclerView recyclerView;

    List<Matches> matchesList=new ArrayList<>();
    List<GameWIthMatchResponsePojo> gameWIthMatchResponsePojosList=new ArrayList<>();
    MatchesListWithGameTitleRecyclerViewAdapter adapter;


    View Fview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_list_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews();
    }

    private void initViews(){
        addItems();
        adapter=new MatchesListWithGameTitleRecyclerViewAdapter(getActivity(),gameWIthMatchResponsePojosList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    public void addItems(){
        matchesList.add(new Matches(1,"Fc Barcelona","Fc Real Madrid"));
        matchesList.add(new Matches(2,"Fc Arcenal","Manchester United"));
        matchesList.add(new Matches(3,"Manchester City","Fc Real Madrid"));
        matchesList.add(new Matches(4,"PSG FC","Fc Real Madrid"));
        matchesList.add(new Matches(5,"Liverpool","FC Totanum"));
        matchesList.add(new Matches(6,"Fc Barcelona","FC Chelase"));

        gameWIthMatchResponsePojosList.add(new GameWIthMatchResponsePojo("UFEA Champions",7,1,matchesList));
        gameWIthMatchResponsePojosList.add(new GameWIthMatchResponsePojo("La Liga",6,2,matchesList));
        gameWIthMatchResponsePojosList.add(new GameWIthMatchResponsePojo("Boundes Liga",4,3,matchesList));
        gameWIthMatchResponsePojosList.add(new GameWIthMatchResponsePojo("Europa Champions Leauge",7,4,matchesList));
        gameWIthMatchResponsePojosList.add(new GameWIthMatchResponsePojo("World Cup",5,5,matchesList));

    }



}
