package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.content.Context;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.google.gson.JsonObject;

public class PayMentMethodBottomPresenter implements PayMentMethodBottomContract.Presenter {
    Context context;
    PayMentMethodBottomContract.View view;
    PayMentMethodBottomRepository repository;

    public PayMentMethodBottomPresenter(Context context, PayMentMethodBottomContract.View view) {
        this.context = context;
        this.view = view;
        this.repository=new PayMentMethodBottomRepository(context,this);
    }

    @Override
    public void sendDataToApi(JsonObject jsonObject) {
        repository.hitPaytmCheckSumApi(jsonObject);
    }

    @Override
    public void getDataFromApi(PaytmChecksumResponsePojo paytmChecksumResponsePojo) {
        view.displayResponse(paytmChecksumResponsePojo);
    }
}
