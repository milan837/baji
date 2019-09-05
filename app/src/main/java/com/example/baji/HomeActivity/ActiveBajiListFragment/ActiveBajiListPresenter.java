package com.example.baji.HomeActivity.ActiveBajiListFragment;

import android.content.Context;

import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.ActiveBajiListResponsePojo;
import com.google.gson.JsonObject;

public class ActiveBajiListPresenter implements ActiveBajiListContract.Presenter {
    Context context;
    ActiveBajiListContract.View view;
    ActiveBajiListRepository repository;

    public ActiveBajiListPresenter(Context context, ActiveBajiListContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new ActiveBajiListRepository(context,this);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ActiveBajiListResponsePojo activeBajiListResponsePojo) {
        view.displayResponse(activeBajiListResponsePojo);
    }
}
