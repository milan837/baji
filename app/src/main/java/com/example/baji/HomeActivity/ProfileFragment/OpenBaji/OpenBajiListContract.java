package com.example.baji.HomeActivity.ProfileFragment.OpenBaji;

import com.example.baji.HomeActivity.ProfileFragment.OpenBaji.Model.ProfileOpenBajiListResponsePojo;
import com.google.gson.JsonObject;

public interface OpenBajiListContract {
    interface  View{
        void displayResponse(ProfileOpenBajiListResponsePojo profileOpenBajiListResponsePojo);
    }

    interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(ProfileOpenBajiListResponsePojo profileOpenBajiListResponsePojo);
    }

}
