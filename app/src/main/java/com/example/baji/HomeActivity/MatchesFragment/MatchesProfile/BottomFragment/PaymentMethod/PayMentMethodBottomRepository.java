package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.content.Context;

import com.google.gson.JsonObject;

public class PayMentMethodBottomRepository {
    Context context;
    PayMentMethodBottomContract.Presenter presenter;

    public PayMentMethodBottomRepository(Context context, PayMentMethodBottomContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public void hitPaytmCheckSumApi(JsonObject jsonObject){

    }
}
