package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBajiResponsePojo;
import com.google.gson.JsonObject;

public class OpenBajiListFragmentPresenter implements OpenBajiListFragmentContract.Presenter {
    Context context;
    OpenBajiListFragmentContract.View view;
    OpenBajiListFragmentRepository repository;

    public OpenBajiListFragmentPresenter(Context context, OpenBajiListFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new OpenBajiListFragmentRepository(context,this);
    }

    @Override
    public void sendDataToApi(ProgressDialog dialog, JsonObject jsonObject) {
        repository.hitApi(dialog,jsonObject);
    }

    @Override
    public void getDataFromApi(OpenBajiResponsePojo openBajiResponsePojo) {
        view.displayResponse(openBajiResponsePojo);
    }
}
