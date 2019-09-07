package com.example.baji.HomeActivity.ProfileFragment.OnboardBaji;

import com.example.baji.HomeActivity.ProfileFragment.OnboardBaji.Model.ProfileOnboardingBajiListResponsePojo;
import com.google.gson.JsonObject;

public class OnboardingBajiListContract {
    interface View{
        void displayResponse(ProfileOnboardingBajiListResponsePojo profileOnboardingBajiListResponsePojo);
    }

    interface Presenter{
        void sendDataFromApi(JsonObject jsonObject);
        void getDataFromApi(ProfileOnboardingBajiListResponsePojo profileOnboardingBajiListResponsePojo);
    }
}
