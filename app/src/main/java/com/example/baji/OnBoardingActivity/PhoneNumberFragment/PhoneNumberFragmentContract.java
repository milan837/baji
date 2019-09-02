package com.example.baji.OnBoardingActivity.PhoneNumberFragment;

import com.example.baji.OnBoardingActivity.OtpFragment.Model.UserRegisterPojo;
import com.google.gson.JsonObject;

public interface PhoneNumberFragmentContract {

    interface View{
        void displayResponseData(UserRegisterPojo userRegisterPojo);
    }

    interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(UserRegisterPojo userRegisterPojo);
    }
}
