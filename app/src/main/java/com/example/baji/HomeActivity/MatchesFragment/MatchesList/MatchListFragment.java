package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    View rootView=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(rootView == null){
           rootView =inflater.inflate(R.layout.fragment_home_match_list_layout,container,false);
           presenter=new MatchesListFragmentPresenter(getActivity(),this);
           presenter.sendDataToApi();
           showProgress();
       }else{
           // when use press back from match profile
       }
       return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initViews();
    }

    private void initViews(){
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
