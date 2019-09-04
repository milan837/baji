package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

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
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.Game;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWithMatchListResponsePojo;
import com.example.baji.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchListFragment extends BaseFragment implements MatchesListFragmentContract.View {

    @BindView(R.id.game_with_match_recycler_view)
    RecyclerView recyclerView;

    MatchesListWithGameTitleRecyclerViewAdapter adapter;
    MatchesListFragmentPresenter presenter;
    List<Game> gameList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_match_list_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        presenter=new MatchesListFragmentPresenter(getActivity(),this);
        initViews();
    }

    private void initViews(){
        presenter.sendDataToApi();
        showProgress();

        adapter=new MatchesListWithGameTitleRecyclerViewAdapter(getActivity(),gameList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();

    }



    @Override
    public void displayResponse(GameWithMatchListResponsePojo gameWithMatchListResponsePojo) {
        dismissProgress();
        gameList.addAll(gameWithMatchListResponsePojo.getGames());
        adapter.notifyDataSetChanged();
    }
}
