package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import android.content.Context;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.AcceptBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
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
    public void sendDataToApi(String amount,String orderId,String customerId) {
        repository.hitPaytmCheckSumApi(amount,orderId,customerId);
    }

    @Override
    public void getDataFromApi(PaytmChecksumResponsePojo paytmChecksumResponsePojo) {
        view.displayResponse(paytmChecksumResponsePojo);
    }

    @Override
    public void sendDataToAcceptBajiApi(JsonObject jsonObject) {
        repository.hitAcceptBajiApi(jsonObject);
    }

    @Override
    public void getDataFromAcceptBajiApi(AcceptBajiResponsePojo acceptBajiResponsePojo) {
        view.displayResponseFromAcceptBajiApi(acceptBajiResponsePojo);
    }

    @Override
    public void sendDataToCreateBajiApi(JsonObject jsonObject) {
        repository.hitCreateNewBajiApi(jsonObject);
    }

    @Override
    public void getDataFromCreateBajiApi(CreateNewBajiResponsePojo createNewBajiResponsePojo) {
        view.displayResponseFromCreateBajiApi(createNewBajiResponsePojo);
    }
}
