package com.example.baji.HomeActivity.MatchesFragment.MatchesList;

import android.content.Context;

import com.example.baji.HomeActivity.MatchesFragment.MatchesList.MatchesListFragmentContract;
import com.example.baji.HomeActivity.MatchesFragment.MatchesList.Model.GameWithMatchListResponsePojo;

public class MatchesListFragmentPresenter implements MatchesListFragmentContract.Presenter {
    Context context;
    MatchesListFragmentContract.View view;
    MatchesListFragmentRepository repository;

    public MatchesListFragmentPresenter(Context context, MatchesListFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new MatchesListFragmentRepository(context,this);
    }

    @Override
    public void sendDataToApi() {
        repository.hitApi();
    }

    @Override
    public void getDataFromApi(GameWithMatchListResponsePojo gameWithMatchListResponsePojo) {
        view.displayResponse(gameWithMatchListResponsePojo);
    }
}
