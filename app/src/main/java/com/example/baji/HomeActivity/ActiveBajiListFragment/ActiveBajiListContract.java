package com.example.baji.HomeActivity.ActiveBajiListFragment;

import com.example.baji.HomeActivity.ActiveBajiListFragment.Model.ActiveBajiListResponsePojo;
import com.google.gson.JsonObject;

public interface ActiveBajiListContract {

    interface View{
        void displayResponse(ActiveBajiListResponsePojo activeBajiListResponsePojo);
    }

    interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(ActiveBajiListResponsePojo activeBajiListResponsePojo);
    }

}
