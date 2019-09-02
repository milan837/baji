package com.example.baji.OnBoardingActivity.SaveInfoFragment;

import com.example.baji.OnBoardingActivity.SaveInfoFragment.Model.SaveUserInfoPojo;
import com.google.gson.JsonObject;

public interface SaveInfoFragmentContract {

    interface View{
        void displayResponse(SaveUserInfoPojo saveUserInfoPojo);
    }

    interface Presenter{
        void sendDataToApi(String authKey,JsonObject jsonObject);
        void getDataFromApi(SaveUserInfoPojo saveUserInfoPojo);
    }
}
