package com.example.baji.OnBoardingActivity.OtpFragment;

import android.content.Context;

import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.google.gson.JsonObject;

public class OtpFragmentPresenter implements OptFragmentContract.Presenter {
    Context context;
    OptFragmentContract.View view;
    OtpFragmentRepositiory repositiory;

    public OtpFragmentPresenter(Context context, OptFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repositiory=new OtpFragmentRepositiory(context,this);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repositiory.registerUserApi(jsonObject);
    }

    @Override
    public void getDataFromApi(UserRegisterPojo userRegisterPojo) {
        view.displayResponseData(userRegisterPojo);
    }
}
