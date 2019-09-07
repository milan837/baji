package com.example.baji.HomeActivity.ProfileFragment.OpenBaji;

import android.content.Context;

import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model.ProfileOpenBajiListResponsePojo;
import com.google.gson.JsonObject;

public class OpenBajiListPresenter implements OpenBajiListContract.Presenter {
    Context context;
    OpenBajiListContract.View view;
    OpenBajiListRepository repository;

    public OpenBajiListPresenter(Context context, OpenBajiListContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new OpenBajiListRepository(context,this);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ProfileOpenBajiListResponsePojo profileOpenBajiListResponsePojo) {
        view.displayResponse(profileOpenBajiListResponsePojo);
    }
}
