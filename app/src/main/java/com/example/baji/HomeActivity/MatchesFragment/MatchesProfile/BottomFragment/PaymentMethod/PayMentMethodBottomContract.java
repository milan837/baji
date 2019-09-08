package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.google.gson.JsonObject;

public class PayMentMethodBottomContract {
    interface View{
        void displayResponse(PaytmChecksumResponsePojo paytmChecksumResponsePojo);
    }
    interface Presenter{
        void sendDataToApi(JsonObject jsonObject);
        void getDataFromApi(PaytmChecksumResponsePojo paytmChecksumResponsePojo);
    }
}
