package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment;

import android.app.Dialog;
import android.app.ProgressDialog;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.OpenBajiListFragment.Model.OpenBajiResponsePojo;
import com.google.gson.JsonObject;

public interface OpenBajiListFragmentContract {

    interface View{
        void displayResponse(OpenBajiResponsePojo openBajiResponsePojo);
    }

    interface Presenter{
        void sendDataToApi(ProgressDialog dialog, JsonObject jsonObject);
        void getDataFromApi(OpenBajiResponsePojo openBajiResponsePojo);
    }
}
