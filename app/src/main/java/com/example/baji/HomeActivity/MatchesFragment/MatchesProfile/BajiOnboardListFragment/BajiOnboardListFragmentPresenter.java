package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment;

import android.content.Context;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.BajiOnboardResponsePojo;
import com.google.gson.JsonObject;

public class BajiOnboardListFragmentPresenter implements BajiOnboardListFragmentContract.Presenter {

    Context context;
    BajiOnboardListFragmentContract.View view;
    BajiOnboardListFragmentRepository repository;

    public BajiOnboardListFragmentPresenter(Context context, BajiOnboardListFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new BajiOnboardListFragmentRepository(context,this);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(BajiOnboardResponsePojo bajiOnboardResponsePojo) {
        view.displayResponse(bajiOnboardResponsePojo);
    }
}
