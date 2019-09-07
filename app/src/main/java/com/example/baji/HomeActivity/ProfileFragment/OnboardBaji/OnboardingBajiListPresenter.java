package com.example.baji.HomeActivity.ProfileFragment.OnboardBaji;

import android.content.Context;

import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.Model.ProfileOnboardingBajiListResponsePojo;
import com.google.gson.JsonObject;

public class OnboardingBajiListPresenter implements OnboardingBajiListContract.Presenter {
    Context context;
    OnboardingBajiListContract.View view;
    OnboardingBajiListRepository repository;

    public OnboardingBajiListPresenter(Context context, OnboardingBajiListContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new OnboardingBajiListRepository(context,this);
    }

    @Override
    public void sendDataFromApi(JsonObject jsonObject) {
        repository.hitApi(jsonObject);
    }

    @Override
    public void getDataFromApi(ProfileOnboardingBajiListResponsePojo profileOnboardingBajiListResponsePojo) {
        view.displayResponse(profileOnboardingBajiListResponsePojo);
    }
}
