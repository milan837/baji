package com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod;

import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.AcceptBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.CreateNewBajiResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.PaytmChecksumResponsePojo;
import com.example.baji.HomeActivity.MatchesFragment.MatchesProfile.BottomFragment.PaymentMethod.Model.TransactionResponsePojo;
import com.google.gson.JsonObject;

public class PayMentMethodBottomContract {
    interface View{
        void displayResponse(PaytmChecksumResponsePojo paytmChecksumResponsePojo);
        void displayResponseFromAcceptBajiApi(AcceptBajiResponsePojo acceptBajiResponsePojo);
        void displayResponseFromCreateBajiApi(CreateNewBajiResponsePojo createNewBajiResponsePojo);
        void displayResponseFromTransactionApi(TransactionResponsePojo transactionResponsePojo);
    }
    interface Presenter{
        //paytm checksum api call
        void sendDataToApi(String amount,String orderId,String customerId);
        void getDataFromApi(PaytmChecksumResponsePojo paytmChecksumResponsePojo);

        //accept open baji api
        void sendDataToAcceptBajiApi(JsonObject jsonObject);
        void getDataFromAcceptBajiApi(AcceptBajiResponsePojo acceptBajiResponsePojo);

        //accept open baji api
        void sendDataToCreateBajiApi(JsonObject jsonObject);
        void getDataFromCreateBajiApi(CreateNewBajiResponsePojo createNewBajiResponsePojo);

        //inserting transaction details
        void sendDataToTransactionApi(JsonObject jsonObject);
        void getDataFromTransactionApi(TransactionResponsePojo transactionResponsePojo);
    }
}
