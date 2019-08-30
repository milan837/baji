package com.example.baji.OnBoardingActivity.OtpFragment;

import com.example.baji.OnBoardingActivity.OtpFragment.Model.User;
import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.google.gson.JsonObject;

public interface OptFragmentContract {

    interface View{
        void displayResponseData(UserRegisterPojo userRegisterPojo);
    }

    interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(UserRegisterPojo userRegisterPojo);
    }
}
