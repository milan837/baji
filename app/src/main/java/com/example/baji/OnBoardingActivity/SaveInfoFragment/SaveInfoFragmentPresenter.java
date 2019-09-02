package com.example.baji.OnBoardingActivity.SaveInfoFragment;

import android.content.Context;

import com.example.baji.OnBoardingActivity.SaveInfoFragment.Model.SaveUserInfoPojo;
import com.google.gson.JsonObject;

public class SaveInfoFragmentPresenter implements SaveInfoFragmentContract.Presenter {
    Context context;
    SaveInfoFragmentContract.View view;
    SaveInfoFragmentRepository repository;

    public SaveInfoFragmentPresenter(Context context, SaveInfoFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new SaveInfoFragmentRepository(context,this);
    }

    @Override
    public void sendDataToApi(String authKey,JsonObject jsonObject) {
        repository.hitSaveInfoApi(authKey,jsonObject);
    }

    @Override
    public void getDataFromApi(SaveUserInfoPojo saveUserInfoPojo) {
        view.displayResponse(saveUserInfoPojo);
    }
}
