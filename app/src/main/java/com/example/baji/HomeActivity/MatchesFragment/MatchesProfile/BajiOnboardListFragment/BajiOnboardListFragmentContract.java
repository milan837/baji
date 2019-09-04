package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BajiOnboardListFragment.Model.BajiOnboardResponsePojo;
import com.google.gson.JsonObject;

public class BajiOnboardListFragmentContract {

    interface View{
        void displayResponse(BajiOnboardResponsePojo bajiOnboardResponsePojo);
    }

    interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(BajiOnboardResponsePojo bajiOnboardResponsePojo);
    }
}
