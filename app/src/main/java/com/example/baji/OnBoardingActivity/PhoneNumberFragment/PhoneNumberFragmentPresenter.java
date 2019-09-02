package com.example.baji.OnBoardingActivity.PhoneNumberFragment;

import android.content.Context;

import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.google.gson.JsonObject;

public class PhoneNumberFragmentPresenter implements PhoneNumberFragmentContract.Presenter {
    Context context;
    PhoneNumberFragmentContract.View view;
    PhoneNumberFragmentRepositiory repositiory;

    public PhoneNumberFragmentPresenter(Context context, PhoneNumberFragmentContract.View view) {
        this.context = context;
        this.view = view;
        this.repositiory=new PhoneNumberFragmentRepositiory(context,this);
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
